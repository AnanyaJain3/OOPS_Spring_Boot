package com.example.Twitter_Final.controller;

class DeleteRequest{
    private int postID;

    DeleteRequest() {
    }

    DeleteRequest(int postID){
        this.postID = postID;
    }
    public int getPostID() {
        return postID;
    }
}
