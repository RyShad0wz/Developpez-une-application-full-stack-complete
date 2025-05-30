package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CreateCommentRequest;
import com.openclassrooms.mddapi.service.CommentService;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    // Récupérer tous les commentaires d’un article
    @GetMapping("/article/{articleId}")
    public List<CommentDto> getByArticle(@PathVariable Long articleId) {
        return commentService.getByArticle(articleId);
    }

    // Ajouter un commentaire à un article
    @PostMapping
    public CommentDto create(@RequestBody CreateCommentRequest req, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();

        return commentService.create(req, userId);
    }
}
