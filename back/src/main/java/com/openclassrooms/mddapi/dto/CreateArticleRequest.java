package com.openclassrooms.mddapi.dto;

public class CreateArticleRequest {
    private String title;
    private String content;
    private Long authorId;
    private Long topicId;

    public CreateArticleRequest() {}

    public CreateArticleRequest(String title, String content, Long authorId, Long topicId) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.topicId = topicId;
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
}

