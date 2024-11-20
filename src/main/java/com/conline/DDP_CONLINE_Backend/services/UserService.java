package com.conline.DDP_CONLINE_Backend.services;

import com.conline.DDP_CONLINE_Backend.entities.User;
import com.conline.DDP_CONLINE_Backend.repositories.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositoryI userRepositoryI;

    public User findOrCreateUser(String googleId, String email, String firstName, String lastName, String profilePictureUrl) {

        if (userRepositoryI.findByEmail(email).isPresent()) {
            return userRepositoryI.findByEmail(email).get();
        }

        return userRepositoryI.save(User.builder()
                .googleId(googleId)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .profilePictureUrl(profilePictureUrl)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    public User findByEmail(String email){
        return userRepositoryI.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
