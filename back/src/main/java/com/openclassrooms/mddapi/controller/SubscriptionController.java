package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionDto> subscribe(@RequestParam Long userId, @RequestParam Long topicId) {
        return ResponseEntity.ok(subscriptionService.subscribe(userId, topicId));
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribe(@RequestParam Long userId, @RequestParam Long topicId) {
        subscriptionService.unsubscribe(userId, topicId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubscriptionDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUser(userId));
    }
}
