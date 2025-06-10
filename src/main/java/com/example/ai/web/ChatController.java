package com.example.ai.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai.service.ChatAgentService;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatAgentService chatAgentService;

    public ChatController(ChatAgentService chatAgentService) {
        this.chatAgentService = chatAgentService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody String message) {
        String response = chatAgentService.chat(message);
        return ResponseEntity.ok(response);
    }
}
