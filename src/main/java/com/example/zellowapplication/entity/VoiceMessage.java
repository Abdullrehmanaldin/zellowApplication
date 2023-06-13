package com.example.zellowapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "voicemessage")
public class VoiceMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sender;

    @Column
    private String recipient;

    @Column
    private LocalDateTime timestamp;

    @Column
    private String audioUrl;

    @ManyToMany
    private List<User> users;
}
