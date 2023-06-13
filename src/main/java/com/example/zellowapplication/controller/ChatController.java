package com.example.zellowapplication.controller;// ChatController.java

import com.example.zellowapplication.entity.Message;
import com.example.zellowapplication.repository.MessageRepository;
import com.example.zellowapplication.repository.ZelloApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ZelloApiClient zelloApiClient;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        List<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/chat/send")
    public String sendMessage(@RequestParam("sender") String sender,
                              @RequestParam("recipient") String recipient,
                              @RequestParam("content") String content) {
        zelloApiClient.sendMessage(sender, recipient, content);
        // Save the sent message to the database
        Message message = new Message(sender, recipient, content);
        messageRepository.save(message);
        return "redirect:/chat";
    }
}
