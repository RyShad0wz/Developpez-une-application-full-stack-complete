package com.openclassrooms.mddapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objet de transfert pour la réponse d'authentification")
public class AuthenticationResponse {

    @Schema(description = "Token d'authentification")
    private String token;


    public AuthenticationResponse() {}

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
