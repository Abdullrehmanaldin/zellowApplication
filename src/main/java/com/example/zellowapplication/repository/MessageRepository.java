package com.example.zellowapplication.repository;

import com.example.zellowapplication.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
