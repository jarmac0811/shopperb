package com.recruitment.beerRestApiTask.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.recruitment.beerRestApiTask.domain.AuthViewModel;
import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.domain.UserDTO;
import com.recruitment.beerRestApiTask.exceptions.EmailExistsException;
import com.recruitment.beerRestApiTask.services.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static com.recruitment.beerRestApiTask.security.TokenUtil.verifyToken;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;
    UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthViewModel requestAuth = null;
        Gson gson = new Gson();
        try {
            String data=request.getReader().toString();
//            requestAuth = new ObjectMapper().readValue(request.getInputStream(), AuthViewModel.class);
            requestAuth = new Gson().fromJson(request.getReader(), AuthViewModel.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.equals(request.getHeader(JwtProperties.HEADER_AUTH_TYPE), (JwtProperties.FORM_AUTH_TYPE))) {
            return attemptFormAuthentication(Objects.requireNonNull(requestAuth));
        } else if (StringUtils.equals(request.getHeader(JwtProperties.HEADER_AUTH_TYPE), (JwtProperties.GOOGLE_AUTH_TYPE))) {
            return attemptGoogleAuthentication(request, requestAuth);
        } else {
            throw new RuntimeException("Authentication type not recognized");
        }
    }

    private Authentication attemptGoogleAuthentication(HttpServletRequest request, AuthViewModel requestAuth) {
        try {
            verifyToken(request.getHeader(JwtProperties.HEADER_ID_TOKEN), JwtProperties.TOKEN_AUD_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //check if user exists then add or verify
        User user = userService.findByExternalUserId(requestAuth.getUserid());
        if (user == null) {
            UserDTO userDTO = new UserDTO(requestAuth);

            try {
                userService.registerNewUser(userDTO);
            } catch (EmailExistsException e) {
                e.printStackTrace();
            }
        }
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestAuth.getUsername(), "password");
        return this.authenticationManager.authenticate(authenticationTokenRequest);
    }


    private Authentication attemptFormAuthentication(AuthViewModel requestAuth) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestAuth.getUsername(), requestAuth.getPassword());

        return this.authenticationManager.authenticate(authenticationTokenRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String authType = request.getHeader(JwtProperties.HEADER_AUTH_TYPE);

        if (JwtProperties.FORM_AUTH_TYPE.equals(authType)) {
            performSuccessfulFormAuthentication(response, authResult);
        } else if (JwtProperties.GOOGLE_AUTH_TYPE.equals(authType)) {
            performSuccessfulGoogleAuthentication(request, response, authResult);
        }
    }

    private void performSuccessfulGoogleAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
        String idToken = request.getHeader(JwtProperties.HEADER_ID_TOKEN);
        try {
            verifyToken(idToken, JwtProperties.TOKEN_AUD_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareResponse(response, principal, idToken);


    }

    private void performSuccessfulFormAuthentication(HttpServletResponse response, Authentication authResult) throws IOException {
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(String.valueOf(principal.getUserId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));
        prepareResponse(response, principal, token);
    }

    private void prepareResponse(HttpServletResponse response, UserPrincipal principal, String token) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader(JwtProperties.HEADER_AUTHORIZATION, JwtProperties.TOKEN_PREFIX + token);
        HashMap<String, String> map = new HashMap<>();
        map.put("username", principal.getUsername());
        map.put("userid", String.valueOf(principal.getUserId()));
        boolean isAdmin = principal.getAuthorities().stream().anyMatch(a -> a.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
        map.put("admin", String.valueOf(isAdmin));
        response.getWriter().write(new Gson().toJson(map));
    }
}
