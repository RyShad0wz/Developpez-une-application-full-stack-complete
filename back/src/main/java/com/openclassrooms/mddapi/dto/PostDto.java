package com.openclassrooms.mddapi.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Long topicId;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;
}
