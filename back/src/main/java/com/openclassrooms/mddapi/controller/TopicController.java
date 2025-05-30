package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "Topics", description = "Gestion des thèmes")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    // GET /api/topics
    @GetMapping
    @Operation(summary = "Liste tous les thèmes")
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // GET /api/topics/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Récupère un thème par ID")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id);
        return ResponseEntity.ok(toDto(topic));
    }

    // POST /api/topics
    @PostMapping
    @Operation(summary = "Crée un nouveau thème")
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto dto) {
        Topic toSave = new Topic(dto.getName(), dto.getDescription());
        Topic saved = topicService.createTopic(toSave);
        return ResponseEntity.ok(toDto(saved));
    }

    // (Optionnel) PUT /api/topics/{id}
    @PutMapping("/{id}")
    @Operation(summary = "Modifie un thème")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable Long id, @RequestBody TopicDto dto) {
        Topic topic = topicService.getTopicById(id);
        topic.setName(dto.getName());
        topic.setDescription(dto.getDescription());
        Topic updated = topicService.createTopic(topic); // Utilise save pour l’update
        return ResponseEntity.ok(toDto(updated));
    }

    // (Optionnel) DELETE /api/topics/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un thème")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    // Utilitaire de conversion (à améliorer si DTO complexe)
    private TopicDto toDto(Topic t) {
        TopicDto dto = new TopicDto();
        dto.setId(t.getId());
        dto.setName(t.getName());
        dto.setDescription(t.getDescription());
        return dto;
    }
}
