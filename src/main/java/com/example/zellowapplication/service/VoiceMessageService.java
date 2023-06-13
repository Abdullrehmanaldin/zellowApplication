package com.example.zellowapplication.service;

import com.example.zellowapplication.entity.VoiceMessage;
import com.example.zellowapplication.repository.VoiceMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoiceMessageService {
    private final VoiceMessageRepository voiceMessageRepository;
    
    @Autowired
    public VoiceMessageService(VoiceMessageRepository voiceMessageRepository) {
        this.voiceMessageRepository = voiceMessageRepository;
    }
    
    public VoiceMessage saveVoiceMessage(VoiceMessage voiceMessage) {
        return voiceMessageRepository.save(voiceMessage);
    }
    
    public List<VoiceMessage> getAllVoiceMessages() {
        return voiceMessageRepository.findAll();
    }
    
    // Other methods for handling voice message operations
}
