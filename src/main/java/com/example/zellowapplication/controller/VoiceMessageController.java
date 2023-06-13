package com.example.zellowapplication.controller;

import com.example.zellowapplication.entity.VoiceMessage;
import com.example.zellowapplication.service.VoiceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/voice-messages")
public class VoiceMessageController {
    private final VoiceMessageService voiceMessageService;
    
    @Autowired
    public VoiceMessageController(VoiceMessageService voiceMessageService) {
        this.voiceMessageService = voiceMessageService;
    }
    
    @GetMapping
    public String getAllVoiceMessages(Model model) {
        List<VoiceMessage> voiceMessages = voiceMessageService.getAllVoiceMessages();
        model.addAttribute("voiceMessages", voiceMessages);
        return "voice_messages";
    }
    
    @PostMapping
    public String saveVoiceMessage(VoiceMessage voiceMessage) {
        voiceMessageService.saveVoiceMessage(voiceMessage);
        return "redirect:/voice-messages";
    }
    
    // Other methods for handling voice message operations
}
