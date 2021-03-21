package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.UserDTO;
import com.recruitment.beerRestApiTask.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type", "Authorization"})
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserDTO>> getAllUsers() {
         return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {

        return ResponseEntity.ok(this.userService.getUser(id));
    }
    @GetMapping
    @ResponseBody
    public String notRestricted() {
        return "Not Restricted access";
    }
    @GetMapping("/oauth/token")
    @ResponseBody
    public ResponseEntity restricted() {
        return ResponseEntity.ok().build();
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.saveUser(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
    }
}


