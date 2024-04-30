package com.example.Twitter_Final.repo;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Twitter_Final.controller.Post_User;

@Repository
public interface PostRepo extends JpaRepository<Post_User, Integer> {
    List<Post_User> findAllByOrderByDateDesc();
    // Add more custom query methods if needed
}