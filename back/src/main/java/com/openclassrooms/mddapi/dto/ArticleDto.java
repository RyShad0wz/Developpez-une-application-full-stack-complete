// src/main/java/com/openclassrooms/mddapi/dto/ArticleDto.java
package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long topicId;
    private LocalDateTime createdAt;

    public ArticleDto() {}

    public ArticleDto(Long id, String title, String content, Long authorId, Long topicId, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.topicId = topicId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}