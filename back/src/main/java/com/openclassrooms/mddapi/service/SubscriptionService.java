package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import java.util.List;

public interface SubscriptionService {
    SubscriptionDto subscribe(Long userId, Long topicId);
    void unsubscribe(Long userId, Long topicId);
    List<SubscriptionDto> getSubscriptionsByUser(Long userId);
}
