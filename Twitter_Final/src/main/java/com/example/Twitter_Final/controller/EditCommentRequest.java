package com.example.Twitter_Final.controller;

public class EditCommentRequest {
    private String commentBody;
    private int commentID;

    // Constructors
    public EditCommentRequest() {
    }

    // Getters and setters
    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
