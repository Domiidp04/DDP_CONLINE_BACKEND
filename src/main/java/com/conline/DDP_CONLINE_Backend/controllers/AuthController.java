package com.conline.DDP_CONLINE_Backend.controllers;

import com.conline.DDP_CONLINE_Backend.dtos.TokenDto;
import com.conline.DDP_CONLINE_Backend.dtos.UrlDto;
import com.conline.DDP_CONLINE_Backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/url")
    public ResponseEntity<UrlDto> auth() {
        return ResponseEntity.ok(authService.generateAuthUrl());
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) {
        try {
            return ResponseEntity.ok(authService.handleCallback(code));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam("token") String token) {
        try {
            authService.logout(token);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
