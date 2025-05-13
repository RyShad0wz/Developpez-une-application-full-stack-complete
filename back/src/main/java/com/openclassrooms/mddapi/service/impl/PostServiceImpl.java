package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TopicRepository topicRepository;

    private PostDto toDto(Post p) {
        return new PostDto(p.getId(), p.getTitle(), p.getContent(), p.getAuthor().getId(), p.getTopic().getId(), p.getCreatedAt(), null);
    }

    private Post toEntity(PostDto dto) {
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow();
        Topic topic = topicRepository.findById(dto.getTopicId()).orElseThrow();
        Post p = new Post();
        p.setTitle(dto.getTitle()); p.setContent(dto.getContent());
        p.setAuthor(author); p.setTopic(topic);
        p.setCreatedAt(LocalDateTime.now());
        return p;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post saved = postRepository.save(toEntity(postDto));
        return toDto(saved);
    }

    @Override
    public PostDto getPostById(Long id) {
        return postRepository.findById(id).map(this::toDto).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
