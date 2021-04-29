package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.domain.UserDTO;
import com.recruitment.beerRestApiTask.exceptions.EmailExistsException;
import com.recruitment.beerRestApiTask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = {"http://localhost:4200"})
/*       allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type", "Authorization"})*/
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @RequestMapping("/login")
    public boolean login(@RequestBody User requestUser) {
        boolean valid;
        System.out.println("password encoded" + passwordEncoder.encode(requestUser.getPassword()));
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestUser.getUserName(), requestUser.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

//            User user = (User) authentication.getPrincipal();
            valid = true;
            //...

        } catch (BadCredentialsException ex) {
            // handle User not found exception
            valid = false;
        }
        return valid;
//        return user.getUserName().equals("user") && user.getPassword().equals("password");
    }

//    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {

        return ResponseEntity.ok(this.userService.getUser(Long.valueOf(id)));
    }

    @GetMapping("/logout")
    @ResponseBody
    public String notRestricted() {
        return "{\"value\":\"logged out\"}";
    }

    @GetMapping("/login/oauth2/code/google")
    @ResponseBody
    public ResponseEntity restricted() {
        return ResponseEntity.of(Optional.of("hello")).ok().build();
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.saveUser(userDTO));
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO user;
        try {
            user = this.userService.registerNewUser(userDTO);
        } catch (EmailExistsException e) {
            return ResponseEntity.badRequest().body("Email address already exists");
        }
        return ResponseEntity.created(URI.create("users/" + user.getUserId())).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
    }
}


