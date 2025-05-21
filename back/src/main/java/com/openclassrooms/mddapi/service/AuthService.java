package com.rental.backend.service;

import com.rental.backend.dto.*;
import com.rental.backend.entity.User;
import com.rental.backend.exception.InvalidCredentialsException;
import com.rental.backend.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthService(UserRepository userRepository,
                     JwtService jwtService,
                     PasswordEncoder passwordEncoder,
                     AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  // 1) Inscription
  public AuthenticationResponse register(RegisterRequest request) {
    // Vérifier si email déjà utilisé, etc.
    // ...
    var user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    // Hachage du mot de passe
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());
    userRepository.save(user);

    // Générer un token
    var userDetails = org.springframework.security.core.userdetails.User.builder()
      .username(user.getEmail())
      .password(user.getPassword()) // haché
      .authorities("ROLE_USER")
      .build();

    String token = jwtService.generateToken(userDetails);

    return new AuthenticationResponse(token);
  }


  public AuthenticationResponse login(LoginRequest request) {

    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
        )
      );
    } catch (AuthenticationException e) {
      throw new InvalidCredentialsException("Email ou mot de passe invalide");
    }


    var user = userRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

    var userDetails = org.springframework.security.core.userdetails.User.builder()
      .username(user.getEmail())
      .password(user.getPassword())
      .authorities("ROLE_USER")
      .build();

    String token = jwtService.generateToken(userDetails);

    return new AuthenticationResponse(token);
  }
}

