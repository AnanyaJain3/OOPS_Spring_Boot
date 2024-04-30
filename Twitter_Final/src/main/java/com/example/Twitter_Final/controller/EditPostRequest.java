package com.example.Twitter_Final.controller;

public class EditPostRequest {
    private String postBody;
    private int postID;

    // Constructors
    public EditPostRequest() {
    }

    // Getters and setters
    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
}
