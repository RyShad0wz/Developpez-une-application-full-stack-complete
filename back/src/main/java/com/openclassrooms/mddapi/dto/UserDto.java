package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Objet de transfert pour les utilisateurs")
public class UserDto {

    @Schema(description = "Identifiant de l'utilisateur", example = "1")
    private Long id;

    @Schema(description = "Nom de l'utilisateur", example = "John Doe")
    private String name;

    @Schema(description = "Adresse email de l'utilisateur", example = "john@example.com")
    private String email;

    public UserDto() {}

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
