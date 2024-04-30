package com.example.Twitter_Final.controller;

class CommentResponse {
    private int commentID;
    private String commentBody;
    private CommentCreator commentCreator;

    public CommentResponse() {
    }

    CommentResponse(int commentID, String commentBody, CommentCreator commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    public int getCommentID() {
        return commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

}
