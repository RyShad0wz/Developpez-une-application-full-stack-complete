package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long topicId;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;
}
