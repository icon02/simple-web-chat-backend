package io.github.icon02.simplewebchatbackend.service;

import io.github.icon02.simplewebchatbackend.entity.Message;
import io.github.icon02.simplewebchatbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getConversation(Long userId1, Long userId2) {
        Pageable pageable = Pageable.unpaged();
        List<Message> messages = messageRepository.getForUser(userId1, userId2, pageable);

        return messages;
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
