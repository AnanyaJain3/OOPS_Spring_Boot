package com.example.Twitter_Final.service;

import com.example.Twitter_Final.controller.*;
import com.example.Twitter_Final.repo.CommentRepo;
import com.example.Twitter_Final.repo.PostRepo;
import com.example.Twitter_Final.repo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

class ErrorResponse {
    public String Error;

    public ErrorResponse(String error) {
        this.Error = error;
    }

}

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepository;

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private userrepo userRepository;

    public ResponseEntity<?> createComment(CommentRequest commentRequest) {
        // Check if user exists
        AppUser user = userRepository.findById(commentRequest.getUserID()).orElse(null);
        if (user == null) {
            ErrorResponse errorResponse = new ErrorResponse("User does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Check if post exists
        Post_User post = postRepository.findById(commentRequest.getPostID()).orElse(null);
        if (post == null) {
            ErrorResponse errorResponse = new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Create a new comment
        Comment comment = new Comment(commentRequest.getCommentBody());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);

        return new ResponseEntity<>("Comment created successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getComment(int commentID) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        CommentCreator commentCreator = new CommentCreator();
        commentCreator.setName(comment.getUser().getName());
        commentCreator.setUserId(comment.getUser().getId());

        CommentResponse commentResponse = new CommentResponse(comment.getId(), comment.getCommentBody(), commentCreator);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> editComment(EditCommentRequest editCommentRequest) {
        int commentId = editCommentRequest.getCommentID();
        String newCommentBody = editCommentRequest.getCommentBody();

        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        comment.setCommentBody(newCommentBody);
        commentRepository.save(comment);

        return new ResponseEntity<>("Comment edited successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        commentRepository.delete(comment);

        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }
}
