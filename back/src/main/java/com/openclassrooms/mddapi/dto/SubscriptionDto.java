package com.openclassrooms.mddapi.dto;

import lombok.*;

@Data
public class SubscriptionDto {
    private Long id;
    private Long userId;
    private Long topicId;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Long id, Long userId, Long topicId) {
        this.id = id;
        this.userId = userId;
        this.topicId = topicId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}