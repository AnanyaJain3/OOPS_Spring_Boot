package com.example.Twitter_Final.controller;


import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public  class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    // Getters, setters, constructors

    // Assuming one-to-many relationship between User and Post
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post_User> posts;


    public AppUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public AppUser() {

    }




    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post_User> getPosts() {
        return posts;
    }

    public void setPosts(List<Post_User> posts) {
        this.posts = posts;
    }

}