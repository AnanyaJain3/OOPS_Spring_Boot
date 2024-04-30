package com.example.Twitter_Final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Twitter_Final.service.userservice;
import com.example.Twitter_Final.service.PostService;


@RestController
public class usercontroller {

    private final userservice userservice;

    @Autowired
    public usercontroller(userservice userService) {
        this.userservice = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginREQ loginRequest) {
        return userservice.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody AppUser user) {
        return userservice.signUp(user);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        return userservice.getUserDetails(userID);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return userservice.getAllUsers();
    }
}














