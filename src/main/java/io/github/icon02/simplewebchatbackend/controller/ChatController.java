package io.github.icon02.simplewebchatbackend.controller;

import io.github.icon02.simplewebchatbackend.entity.Message;
import io.github.icon02.simplewebchatbackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/chat/conversation")
    public List<Message> getMessagesForUser(@RequestParam("userId1") Long userId1, @RequestParam("userId2") Long userId2) {
        return chatService.getConversation(userId1, userId2);
    }

    @MessageMapping("/chat/{toUserId}")
    public void handleMessage(@DestinationVariable Long toUserId, Message message) {
        System.out.println("Sending message to: " + toUserId + "; message: " + message.getMessage());
        chatService.saveMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + toUserId, message);
    }
}
