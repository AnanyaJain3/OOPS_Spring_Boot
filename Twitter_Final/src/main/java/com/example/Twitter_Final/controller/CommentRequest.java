package com.example.Twitter_Final.controller;

public class CommentRequest {
    private String commentBody;
    private int postID;
    private int userID;

    // Constructors
    public CommentRequest() {
    }

    // Getters and setters

    public String getCommentBody() {
        return commentBody;
    }

    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
