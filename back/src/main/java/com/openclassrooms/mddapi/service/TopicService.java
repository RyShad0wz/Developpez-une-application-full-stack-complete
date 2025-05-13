package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicDto;
import java.util.List;

public interface TopicService {
    TopicDto createTopic(TopicDto topicDto);
    TopicDto getTopicById(Long id);
    List<TopicDto> getAllTopics();
}

