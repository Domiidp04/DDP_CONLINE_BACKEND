package com.conline.DDP_CONLINE_Backend.dtos;

public record UserInfo(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        String email,
        boolean email_verified,
        String locale
) { }
