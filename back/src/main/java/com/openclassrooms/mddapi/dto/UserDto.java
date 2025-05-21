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

    @Schema(description = "Date de création de l'utilisateur", example = "2021-06-01")
    @JsonFormat(pattern = "yyyy/MM/dd") // Format de date personnalisé
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Date de mise à jour de l'utilisateur", example = "2021-06-01")
    @JsonFormat(pattern = "yyyy/MM/dd") // Format de date personnalisé
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public UserDto() {}

    public UserDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
