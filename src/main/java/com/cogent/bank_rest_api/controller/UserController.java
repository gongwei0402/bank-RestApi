package com.cogent.bank_rest_api.controller;

import com.cogent.bank_rest_api.Entity.User;
import com.cogent.bank_rest_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User newUser){
        User user = userService.registerUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
