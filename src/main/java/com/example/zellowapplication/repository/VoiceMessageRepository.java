package com.example.zellowapplication.repository;

import com.example.zellowapplication.entity.VoiceMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceMessageRepository extends JpaRepository<VoiceMessage, Long> {
    // Custom query methods can be added here if needed
}
