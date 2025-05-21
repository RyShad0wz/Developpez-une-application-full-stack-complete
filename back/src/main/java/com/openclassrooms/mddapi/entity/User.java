package com.openclassrooms.mddapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique", example = "1")
    private Long id;

    @Schema(description = "Adresse email", example = "john@example.com")
    @Column(unique = true)
    private String email;

    @Schema(description = "Nom de l'utilisateur", example = "John Doe")
    private String name;

    @Schema(description = "Mot de passe", example = "secret")
    private String password;

    @Schema(description = "Date de création de l'utilisateur")
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Date de mise à jour de l'utilisateur")
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public User() {

    }

    public User(Long id, String email, String name, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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