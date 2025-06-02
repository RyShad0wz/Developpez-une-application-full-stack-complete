package com.openclassrooms.mddapi.config;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicSeeder {

    @Autowired
    private TopicRepository topicRepository;

    @PostConstruct
    public void seedTopics() {
        // Liste des topics à créer
        List<Topic> topics = List.of(
                new Topic("Java", "Toute l'actualité concernant Java"),
                new Topic("Développement", "Discussions sur le développement logiciel, frameworks, etc."),
                new Topic("Python", "Retrouvez toutes les news sur Python"),
                new Topic("React", "Toutes les infos sur React et son écosystème"),
                new Topic("JavaScript", "Les dernières tendances en JavaScript")
        );

        for (Topic topic : topics) {
            Topic existing = topicRepository.findByName(topic.getName());
            if (existing != null) {
                existing.setDescription(topic.getDescription());
                topicRepository.save(existing);
            } else {
                topicRepository.save(topic);
            }
        }
    }
}
