package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.entity.Subscription;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;

    public void subscribe(Long userId, Long topicId) {
        if (subscriptionRepository.existsByUserIdAndTopicId(userId, topicId)) return;
        Subscription sub = new Subscription();
        sub.setUser(userRepository.findById(userId).orElseThrow());
        sub.setTopic(topicRepository.findById(topicId).orElseThrow());
        subscriptionRepository.save(sub);
    }

    @Transactional
    public void unsubscribe(Long userId, Long topicId) {
        subscriptionRepository.deleteByUserIdAndTopicId(userId, topicId);
    }

    public List<TopicDto> getSubscribedTopics(Long userId) {
        // Utilise la requête qui va vraiment chercher les Topic
        return subscriptionRepository.findTopicsByUserId(userId).stream()
                .map(topic -> new TopicDto(topic.getId(), topic.getName(), topic.getDescription()))
                .collect(Collectors.toList());
    }

}

