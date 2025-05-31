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
                new Topic("Actualités", "Toute l'actualité récente et chaude."),
                new Topic("Développement", "Discussions sur le développement logiciel, frameworks, etc."),
                new Topic("Ecologie", "Partage de conseils et actualités autour de l'écologie."),
                new Topic("Sport", "Actualités, résultats et débats sportifs."),
                new Topic("Culture", "Cinéma, musique, livres et tous les loisirs culturels.")
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
