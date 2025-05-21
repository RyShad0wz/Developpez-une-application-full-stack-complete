/* package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    private CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor().getId(),
                comment.getArticle().getId(),
                comment.getCreatedAt()
        );
    }

    private Comment toEntity(CommentDto dto) {
        User author = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(dto.getArticleId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setAuthor(author);
        comment.setArticle(post);
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment saved = commentRepository.save(toEntity(commentDto));
        return toDto(saved);
    }

    @Override
    public List<CommentDto> getCommentsByPost(Long postId) {
        return null;
    }

    @Override
    public List<CommentDto> getCommentsByArticle(Long articleId) {
        return commentRepository.findAll().stream()
                .filter(c -> c.getArticle().getId().equals(articleId))
                .map(this::toDto)
                .collect(Collectors.toList());
    }
} */
