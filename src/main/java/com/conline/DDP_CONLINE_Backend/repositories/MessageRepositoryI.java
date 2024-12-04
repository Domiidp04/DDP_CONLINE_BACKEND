package com.conline.DDP_CONLINE_Backend.repositories;

import com.conline.DDP_CONLINE_Backend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepositoryI extends JpaRepository<Message, Long> {
    Optional<Message> findTopByOrderByTimestampDesc();
}
