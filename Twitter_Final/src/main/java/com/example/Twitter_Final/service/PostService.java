package com.example.Twitter_Final.service;

import com.example.Twitter_Final.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Twitter_Final.repo.PostRepo;
import com.example.Twitter_Final.repo.userrepo;
import com.example.Twitter_Final.controller.AppUser;
import com.example.Twitter_Final.controller.Post_User;
import com.example.Twitter_Final.controller.Comment;
import com.example.Twitter_Final.controller.CommentCreator;
import com.example.Twitter_Final.controller.PostRequest;
import com.example.Twitter_Final.controller.EditPostRequest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



class PostResponse {
    private int postID;
    private String postBody;
    private Date date;
    private int userId;
    private List<CommentResponse> comments;

    PostResponse(int postID, String postBody, Date date, int userId, List<CommentResponse> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.userId = userId;
        this.comments = comments;
    }

    public PostResponse() {
    }

    public int getPostID() {
        return postID;
    }


    public String getPostBody() {
        return postBody;
    }

    public  int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

}

@Service
public class PostService {

    private final PostRepo postRepository;
    private final userrepo userRepository;

    @Autowired
    public PostService(PostRepo postRepository, userrepo userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getUserFeed() {
        List<Post_User> posts = postRepository.findAllByOrderByDateDesc();
        List<PostResponse> postResponses = posts.stream()
                .map(this::mapPostToPostResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    public ResponseEntity<?> createPost(PostRequest postRequest) {
        AppUser user = userRepository.findById(postRequest.getUserID()).orElse(null);
        if (user == null) {
            ErrorResponse error=new ErrorResponse("User does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        Post_User post = new Post_User(postRequest.getPostBody(), new Date());
        post.setUser(user);
        postRepository.save(post);
        return new ResponseEntity<>("Post created successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getPost(int postID) {
        Post_User post = postRepository.findById(postID).orElse(null);
        if (post == null) {
            ErrorResponse error=new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        PostResponse postResponse = mapPostToPostResponse(post);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> editPost(EditPostRequest editPostRequest) {
        int postId = editPostRequest.getPostID();
        String newPostBody = editPostRequest.getPostBody();

        Post_User post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            ErrorResponse error=new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        post.setPostBody(newPostBody);
        postRepository.save(post);

        return new ResponseEntity<>("Post edited successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> deletePost(int postID) {
        Post_User post = postRepository.findById(postID).orElse(null);
        if (post == null) {
            ErrorResponse error=new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        postRepository.delete(post);

        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    private PostResponse mapPostToPostResponse(Post_User post) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentCreator commentCreator = new CommentCreator();
            commentCreator.setName(comment.getUser().getName());
            commentCreator.setUserId(comment.getUser().getId());
            commentResponses.add(new CommentResponse(comment.getId(), comment.getCommentBody(), commentCreator));
        }
        return new PostResponse(post.getId(), post.getPostBody(), post.getDate(), post.getUser().getId(), commentResponses);
    }

    class ErrorResponse {
        public String Error;

        public ErrorResponse(String error) {
            this.Error = error;
        }

    }
}
