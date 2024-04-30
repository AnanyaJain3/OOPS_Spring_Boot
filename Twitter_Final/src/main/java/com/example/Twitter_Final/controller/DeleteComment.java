package com.example.Twitter_Final.controller;

class DeleteComment{
    private int commentID;
    DeleteComment(){
    }
    DeleteComment(int id){
        this.commentID = id;
    }

    public int getCommentID() {
        return commentID;
    }
}