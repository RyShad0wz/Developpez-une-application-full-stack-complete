package com.openclassrooms.mddapi.config;


import com.openclassrooms.mddapi.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;



@Configuration
public class SecurityConfig {

  @Autowired
  private CorsConfigurationSource corsConfigurationSource;

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,
                        AuthenticationProvider authenticationProvider) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.authenticationProvider = authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(cors -> cors.configurationSource(corsConfigurationSource))
      .authorizeHttpRequests(auth -> {
        // Autoriser l’accès public aux endpoints d’authentification et aux GET sur les utilisateurs et rentals
        auth.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
        auth.requestMatchers("/api/auth/register", "/api/auth/login").permitAll();
        auth.requestMatchers("/api/rentals/**", "/api/messages/**", "/api/user/**", "/api/auth/me").authenticated();
      })
      .exceptionHandling(exceptions -> exceptions
        .authenticationEntryPoint(customAuthenticationEntryPoint()) // Gestion personnalisée des erreurs d'authentification
      )
      .authenticationProvider(authenticationProvider)
      // Ajout du filtre JWT
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationEntryPoint customAuthenticationEntryPoint() {
    return (HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) -> {
      response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Renvoyer une 401
      response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Définir le type de contenu
      response.getWriter().write("{\"message\": \"error\"}"); // Corps de la réponse
    };
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
