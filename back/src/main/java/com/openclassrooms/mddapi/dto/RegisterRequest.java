package com.rental.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objet de transfert pour l'enregistrement")
public class RegisterRequest {

  @Schema(description = "Nom de l'utilisateur", example = "John Doe")
  private String name;

  @Schema(description = "Adresse email de l'utilisateur", example = "john@example.com")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur", example = "pswd")
  private String password;

  // getters, setters


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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
