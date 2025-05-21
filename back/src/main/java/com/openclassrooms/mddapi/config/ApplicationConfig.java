package com.openclassrooms.mddapi.config;

import com.openclassrooms.mddapi.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Schema(description = "Configuration de l'application")
public class ApplicationConfig {

  @Schema(description = "Service pour gérer les utilisateurs")
  private final CustomUserDetailsService userDetailsService;

  public ApplicationConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  @Schema(description = "Encodeur de mot de passe")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Schema(description = "Provider pour l'authentification")
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
}

