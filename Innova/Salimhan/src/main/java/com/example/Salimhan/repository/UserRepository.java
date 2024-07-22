package com.example.Salimhan.repository;

import com.example.Salimhan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //org.springframework.security.core.userdetails.User findByUserName(String username);
}
