package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @PostMapping("/{topicId}")
    public void subscribe(@PathVariable Long topicId, Authentication auth) {
        Long userId = userService.getUserByEmail(auth.getName()).getId();
        subscriptionService.subscribe(userId, topicId);
    }

    @DeleteMapping("/{topicId}")
    public void unsubscribe(@PathVariable Long topicId, Authentication auth) {
        Long userId = userService.getUserByEmail(auth.getName()).getId();
        subscriptionService.unsubscribe(userId, topicId);
    }

    @GetMapping
    public List<TopicDto> getUserSubscriptions(Authentication auth) {
        Long userId = userService.getUserByEmail(auth.getName()).getId();
        return subscriptionService.getSubscribedTopics(userId);
    }
}

