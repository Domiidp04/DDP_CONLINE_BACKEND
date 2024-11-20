package com.conline.DDP_CONLINE_Backend.services;

import com.conline.DDP_CONLINE_Backend.dtos.GoogleUserResponse;
import com.conline.DDP_CONLINE_Backend.dtos.TokenDto;
import com.conline.DDP_CONLINE_Backend.dtos.UrlDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;

    private final UserService userService;

    public UrlDto generateAuthUrl() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId,
                "http://localhost:4200/login",
                Arrays.asList("email", "profile", "openid")).build();
        return new UrlDto(url);
    }

    public TokenDto handleCallback(String code) throws URISyntaxException, IOException {
        // Obtener el token de acceso
        String token = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                new GsonFactory(),
                clientId,
                clientSecret,
                code,
                "http://localhost:4200/login"
        ).execute().getAccessToken();

        // Obtener los datos del usuario
        GoogleUserResponse userInfo = fetchGoogleUserInfo(token);

        // Registrar o buscar al usuario
        userService.findOrCreateUser(
                userInfo.getSub(),
                userInfo.getEmail(),
                userInfo.getGivenName(),
                userInfo.getFamilyName(),
                userInfo.getPicture()
        );

        return new TokenDto(token);
    }

    public void logout(String token) {
        String revokeUrl = "https://oauth2.googleapis.com/revoke";
        WebClient.create()
                .post()
                .uri(revokeUrl)
                .bodyValue("token=" + token)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    private GoogleUserResponse fetchGoogleUserInfo(String token) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory(request -> {
            request.setParser(new GsonFactory().createJsonObjectParser());
        });

        GenericUrl url = new GenericUrl("https://www.googleapis.com/oauth2/v3/userinfo");
        HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setAuthorization("Bearer " + token);

        return request.execute().parseAs(GoogleUserResponse.class);
    }
}

