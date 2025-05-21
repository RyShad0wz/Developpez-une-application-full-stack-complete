package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String content;
    private Long authorId;
    private Long articleId;
    private LocalDateTime createdAt;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, Long authorId, Long articleId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.articleId = articleId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
