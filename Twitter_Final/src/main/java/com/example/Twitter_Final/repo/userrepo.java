package com.example.Twitter_Final.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Twitter_Final.controller.AppUser;


public interface userrepo extends JpaRepository<AppUser, Integer>{
    AppUser findByEmail(String email);
}