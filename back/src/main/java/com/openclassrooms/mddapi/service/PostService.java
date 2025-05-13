package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPostById(Long id);
    List<PostDto> getAllPosts();
}
