package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    boolean existsByUserIdAndTopicId(Long userId, Long topicId);
    void deleteByUserIdAndTopicId(Long userId, Long topicId);
    List<Subscription> findByUserId(Long userId);
}

