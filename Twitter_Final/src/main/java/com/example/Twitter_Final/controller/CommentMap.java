package com.example.Twitter_Final.controller;

import com.example.Twitter_Final.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentMap {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @GetMapping("/comment")
    public ResponseEntity<?> getComment(@RequestParam int commentID) {
        return commentService.getComment(commentID);
    }

    @PatchMapping("/comment")
    public ResponseEntity<?> editComment(@RequestBody EditCommentRequest editCommentRequest) {
        return commentService.editComment(editCommentRequest);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@RequestParam int commentID) {
        return commentService.deleteComment(commentID);
    }
}
