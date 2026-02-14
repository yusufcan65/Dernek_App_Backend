package com.example.odev.service.Impl;

import com.example.odev.service.WebSocketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.example.odev.dto.DuyuruResponse;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;


    public WebSocketServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void duyuruGonder(DuyuruResponse duyuru) {
        messagingTemplate.convertAndSend("/topic/duyuru", duyuru);
    }
}
