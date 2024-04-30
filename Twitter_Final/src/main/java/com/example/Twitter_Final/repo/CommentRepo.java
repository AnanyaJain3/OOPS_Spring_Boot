package com.example.Twitter_Final.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Twitter_Final.controller.Comment;
import com.example.Twitter_Final.controller.Post_User;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Post_User findByPostId(int id);
}
