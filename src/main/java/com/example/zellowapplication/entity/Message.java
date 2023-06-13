package com.example.zellowapplication.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "message")
@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sender;

    @Column
    private String recipient;

    @Column
    private String content;

    @ManyToMany(mappedBy = "messages")
    private List<User> users;


    public Message(String sender, String recipient, String content) {
    }

    public Message() {

    }
}
