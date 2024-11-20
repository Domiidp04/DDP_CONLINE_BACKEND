package com.conline.DDP_CONLINE_Backend.dtos;

import com.google.api.client.util.Key;
import lombok.Data;

@Data
public class GoogleUserResponse {

    @Key("sub")
    private String sub; // ID único del usuario en Google

    @Key("email")
    private String email;

    @Key("given_name")
    private String givenName;

    @Key("family_name")
    private String familyName;

    @Key("picture")
    private String picture; // URL de la foto de perfil
}

