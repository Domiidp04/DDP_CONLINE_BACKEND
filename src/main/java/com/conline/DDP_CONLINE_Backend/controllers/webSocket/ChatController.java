package com.conline.DDP_CONLINE_Backend.controllers.webSocket;

import com.conline.DDP_CONLINE_Backend.dtos.ChatMessage;
import com.conline.DDP_CONLINE_Backend.dtos.MessageInputDto;
import com.conline.DDP_CONLINE_Backend.entities.Message;
import com.conline.DDP_CONLINE_Backend.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("**")
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload MessageInputDto messageInputDto) {
        return messageService.saveMessage(messageInputDto.senderId(), messageInputDto.content());
    }
}
