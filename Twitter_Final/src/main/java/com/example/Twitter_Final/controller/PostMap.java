package com.example.Twitter_Final.controller;


import com.example.Twitter_Final.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostMap {

    private final PostService postService;

    @Autowired
    public PostMap(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserFeed() {
        return postService.getUserFeed();
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPost(@RequestParam int postID) {
        return postService.getPost(postID);
    }

    @PatchMapping("/post")
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest editPostRequest) {
        return postService.editPost(editPostRequest);
    }

    @DeleteMapping("/post")
    public ResponseEntity<?> deletePost(@RequestParam int postID) {
        return postService.deletePost(postID);
    }
}