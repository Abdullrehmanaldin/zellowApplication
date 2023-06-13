package com.example.zellowapplication.repository;

import com.example.zellowapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MyUserRepository")
public interface MyUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}