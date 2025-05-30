package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CreateArticleRequest;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    // ---- CREATE ----
    @PostMapping
    public ArticleDto create(@RequestBody CreateArticleRequest req, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();
        return articleService.create(req, userId);
    }

    // ---- READ (ALL) ----
    @GetMapping
    public List<ArticleDto> getAll(@RequestParam(defaultValue = "desc") String direction) {
        return articleService.getAll(direction);
    }

    // ---- READ (BY ID) ----
    @GetMapping("/{id}")
    public ArticleDto getById(@PathVariable Long id) {
        return articleService.getById(id);
    }

    // ---- UPDATE ----
    @PutMapping("/{id}")
    public ArticleDto update(@PathVariable Long id, @RequestBody CreateArticleRequest req, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();
        return articleService.update(id, req, userId);
    }

    // ---- DELETE ----
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        UserDto user = userService.getUserByEmail(email);
        Long userId = user.getId();
        articleService.delete(id, userId);
    }
}
