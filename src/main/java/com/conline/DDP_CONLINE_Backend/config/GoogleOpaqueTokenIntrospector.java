package com.conline.DDP_CONLINE_Backend.config;

import com.conline.DDP_CONLINE_Backend.dtos.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final WebClient userInfoClient;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo userInfo = userInfoClient.get()
                .uri( uriBuilder -> uriBuilder
                        .path("/oauth2/v3/userinfo")
                        .queryParam("access_token", token)
                        .build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();

        Map<String, Object> attributes = new HashMap<>();
        assert userInfo != null;
        attributes.put("google_id", userInfo.sub()); // ID de Google
        attributes.put("email", userInfo.email()); // Email del usuario
        attributes.put("first_name", userInfo.given_name()); // Primer nombre
        attributes.put("last_name", userInfo.family_name()); // Apellido
        attributes.put("profile_picture_url", userInfo.picture()); // URL de la foto de perfil

        return new OAuth2IntrospectionAuthenticatedPrincipal(userInfo.email(), attributes, null);
    }
}
