package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    private TopicDto toDto(Topic t){return new TopicDto(t.getId(), t.getName(), t.getDescription());}
    private Topic toEntity(TopicDto dto){return new Topic(dto.getId(), dto.getName(), dto.getDescription());}

    @Override
    public TopicDto createTopic(TopicDto topicDto) {
        Topic saved = topicRepository.save(toEntity(topicDto));
        return toDto(saved);
    }

    @Override
    public TopicDto getTopicById(Long id) {
        return topicRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Topic not found"));
    }

    @Override
    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}

