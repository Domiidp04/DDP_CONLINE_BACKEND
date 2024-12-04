package com.conline.DDP_CONLINE_Backend.dtos;

import java.time.LocalDateTime;

public record ChatMessage(String sender, String content, LocalDateTime timestamp) {
}
