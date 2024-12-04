package com.conline.DDP_CONLINE_Backend.services;

import com.conline.DDP_CONLINE_Backend.entities.Message;
import com.conline.DDP_CONLINE_Backend.entities.User;
import com.conline.DDP_CONLINE_Backend.repositories.MessageRepositoryI;
import com.conline.DDP_CONLINE_Backend.repositories.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepositoryI messageRepositoryI;
    private final UserRepositoryI userRepositoryI;

    public Message saveMessage(Long senderId, String content) {
        User sender = userRepositoryI.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message message = Message.builder()
                .sender(sender)
                .content(content)
                .timestamp(LocalDateTime.now())
                .build();

        return messageRepositoryI.save(message);
    }

    public List<Message> getMessages(){
        return this.messageRepositoryI.findAll();
    }

    public Optional<Message> getLastMessage(){
        return this.messageRepositoryI.findTopByOrderByTimestampDesc();
    }
}
