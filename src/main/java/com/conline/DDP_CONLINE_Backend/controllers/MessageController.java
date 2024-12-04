package com.conline.DDP_CONLINE_Backend.controllers;

import com.conline.DDP_CONLINE_Backend.entities.Message;
import com.conline.DDP_CONLINE_Backend.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getMessages(){
        return this.messageService.getMessages();
    }

    @GetMapping("/lastMessage")
    public Optional<Message> getLastMessage(){
        return this.messageService.getLastMessage();
    }
}
