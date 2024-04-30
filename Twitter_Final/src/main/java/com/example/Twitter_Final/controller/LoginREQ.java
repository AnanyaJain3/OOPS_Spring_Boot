package com.example.Twitter_Final.controller;


public class LoginREQ {
    private String email;
    private String password;

    LoginREQ(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
