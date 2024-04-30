package com.example.Twitter_Final.controller;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Post_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String postBody;

    @Temporal(TemporalType.DATE)
    private Date date;

    // Assuming many-to-one relationship between Post and User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    // Assuming one-to-many relationship between Post and Comment
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    // Constructors
    public Post_User() {
    }

    public Post_User(String postBody, Date date) {
        this.postBody = postBody;
        this.date = date;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return  comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
