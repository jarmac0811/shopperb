package com.recruitment.beerRestApiTask.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.recruitment.beerRestApiTask.security.TokenUtil.decodeToJson;
import static com.recruitment.beerRestApiTask.security.TokenUtil.verifyToken;

public class JvtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserRepository userRepository;

    public JvtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_AUTHORIZATION).replace(JwtProperties.TOKEN_PREFIX, "");
        DecodedJWT jwt = JWT.decode(token);
        String headerJsonString = decodeToJson(jwt.getHeader());
        JsonObject headerJsonObj = new Gson().fromJson(headerJsonString, JsonObject.class);
        String algorithm = headerJsonObj.get("alg").getAsString();
        String bodyJsonString = decodeToJson(jwt.getPayload());
        JsonObject bodyJsonObj = new Gson().fromJson(bodyJsonString, JsonObject.class);
        String subject = bodyJsonObj.get("sub").getAsString();
        User user = null;
        if (JwtProperties.HMAC512_ALG_TYPE.equals(algorithm)) {
            JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token);
            user = userRepository.findByUserId(Long.valueOf(subject));

//            throw new JWTVerificationException("invalid token");
        } else if (JwtProperties.RS256_ALG_TYPE.equals(algorithm)) {
            try {
                verifyToken(token, JwtProperties.TOKEN_AUD_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            user = userRepository.findByExternalUserId(subject);
//            throw new JWTVerificationException("invalid token");
        }
        return JvtAuthorizationFilter.this.getAuthentication(token, user);

    }

    private Authentication getAuthentication(String token, User user) {
        Authentication authentication;
        UserPrincipal principal = new UserPrincipal(user);
        authentication = new
                UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
        return authentication;
    }


}
