package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.*;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Endpoints pour la gestion des utilisateurs")
public class UserController {

    private final UserService userService;

    private final SubscriptionService subscriptionService;

    @Autowired
    public UserController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par son ID", description = "Récupère les détails d'un utilisateur spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<UserDto> getUser(
            @Parameter(description = "ID de l'utilisateur", example = "1", required = true)
            @PathVariable Long id
    ) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/me")
    public UserProfileDto getProfile(Authentication auth) {
        UserDto user = userService.getUserByEmail(auth.getName());
        List<TopicDto> subs = subscriptionService.getSubscribedTopics(user.getId());
        return new UserProfileDto(user.getName(), user.getEmail(), subs);
    }

    @PutMapping("/me")
    public UserDto updateCurrentUser(@RequestBody UpdateUserRequest req, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        return userService.updateUser(user.getId(), req);
    }

    @DeleteMapping("/me/subscriptions/{topicId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable Long topicId,
            Authentication authentication
    ) {
        UserDto user = userService.getUserByEmail(authentication.getName());
        subscriptionService.unsubscribe(user.getId(), topicId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest req, Authentication auth) {
        userService.updatePassword(auth.getName(), req);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {

        return userService.updateUser(id, request);
    }


    @PostMapping("/register")
    @Operation(summary = "Créer un nouvel utilisateur", description = "Enregistre un nouvel utilisateur avec les informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Données de l'utilisateur invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<UserDto> registerUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données de l'utilisateur à créer",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRegistrationRequest.class)))
            @Valid @RequestBody UserRegistrationRequest request
    ) {
        UserDto dto = new UserDto();
        dto.setName(request.getName());
        dto.setEmail(request.getEmail());
        UserDto createdUser = userService.createUser(dto, request.getPassword());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
