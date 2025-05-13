package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.entity.Subscription;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired private SubscriptionRepository subRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TopicRepository topicRepo;

    private SubscriptionDto toDto(Subscription s) {
        return new SubscriptionDto(s.getId(), s.getUser().getId(), s.getTopic().getId());
    }

    @Override
    public SubscriptionDto subscribe(Long userId, Long topicId) {
        User u = userRepo.findById(userId).orElseThrow();
        Topic t = topicRepo.findById(topicId).orElseThrow();
        Subscription s = new Subscription(); s.setUser(u); s.setTopic(t);
        return toDto(subRepo.save(s));
    }

    @Override
    public void unsubscribe(Long userId, Long topicId) {
        subRepo.findAll().stream()
                .filter(s -> s.getUser().getId().equals(userId) && s.getTopic().getId().equals(topicId))
                .findFirst().ifPresent(subRepo::delete);
    }

    @Override
    public List<SubscriptionDto> getSubscriptionsByUser(Long userId) {
        return subRepo.findAll().stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
