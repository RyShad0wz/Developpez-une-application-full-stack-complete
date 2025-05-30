package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CreateCommentRequest;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public List<CommentDto> getByArticle(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleIdOrderByCreatedAt(articleId);
        return comments.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CommentDto create(CreateCommentRequest req, Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        Article article = articleRepository.findById(req.getArticleId())
                .orElseThrow(() -> new ResourceNotFoundException("Article non trouvé"));

        Comment comment = new Comment();
        comment.setContent(req.getContent());
        comment.setAuthor(author);
        comment.setArticle(article);
        comment.setCreatedAt(LocalDateTime.now());

        comment = commentRepository.save(comment);
        return toDto(comment);
    }

    // (update et delete si tu veux faire un CRUD complet)
    // ...

    private CommentDto toDto(Comment c) {
        return new CommentDto(
                c.getId(),
                c.getContent(),
                c.getAuthor().getId(),
                c.getAuthor().getName(),
                c.getArticle().getId(),
                c.getCreatedAt()
        );
    }
}
