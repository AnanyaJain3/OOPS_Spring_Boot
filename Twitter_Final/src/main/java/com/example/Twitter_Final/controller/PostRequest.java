package com.example.Twitter_Final.controller;


public class PostRequest {
    private String postBody;
    private int userID;

    // Constructors
    public PostRequest() {
    }

    // Getters and setters
    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

