package com.example.Twitter_Final.service;

import com.example.Twitter_Final.controller.*;
import com.example.Twitter_Final.repo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class UserResponse {
    private int userID;
    private String name;
    private String email;

    public UserResponse(String name,int userID,  String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;


    }


    public String getEmail() {
        return email;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

}

@Service
public class userservice {
    class ErrorResponse {
        public String Error;

        public ErrorResponse(String error) {
            this.Error = error;
        }

    }

    private final userrepo userRepository;

    @Autowired
    public userservice(userrepo userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> signUp(AppUser user) {
        AppUser existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            ErrorResponse error=new ErrorResponse("Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
        userRepository.save(user);
        return ResponseEntity.ok("Account Creation Successful");
    }

    public ResponseEntity<?> getUserDetails(int userID) {
      AppUser user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            ErrorResponse error=new ErrorResponse("User does not exist");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        List<PostResponse> postResponses = user.getPosts().stream()
                .map(post ->  new PostResponse(post.getId(), post.getPostBody(), post.getDate(),
                        post.getUser().getId(), mapCommentsToCommentResponses(post.getComments())))
                .collect(Collectors.toList());

        UserResponse userResponse = new UserResponse(user.getName(), user.getId(), user.getEmail());

        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<?> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(user -> new UserResponse(user.getName(), user.getId(), user.getEmail()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    private List<CommentResponse> mapCommentsToCommentResponses(List<Comment> comments) {

        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentCreator commentCreator = new CommentCreator();
            commentCreator.setName(comment.getUser().getName());
            commentCreator.setUserId(comment.getUser().getId());
            commentResponses.add(new CommentResponse(comment.getId(), comment.getCommentBody(), commentCreator));
        }
        return commentResponses;

    }

    public ResponseEntity<?> login(LoginREQ loginRequest) {
        AppUser user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            ErrorResponse error=new ErrorResponse("User does not exist");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } else if (!user.getPassword().equals(loginRequest.getPassword())) {
            ErrorResponse error=new ErrorResponse("Username/Password Incorrect");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        } else {
            return ResponseEntity.ok("Login Successful");
        }
    }
}
