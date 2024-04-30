package com.example.Twitter_Final.controller;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.persistence.*;
import java.util.stream.Collectors;




@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String commentBody;

    // Assuming many-to-one relationship between Comment and Post
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post_User post;

    // Assuming many-to-one relationship between Comment and User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    // Constructors
    public Comment() {
    }

    public Comment(String commentBody) {
        this.commentBody = commentBody;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Post_User getPost() {
        return post;
    }

    public void setPost(Post_User post) {
        this.post = post;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

}

