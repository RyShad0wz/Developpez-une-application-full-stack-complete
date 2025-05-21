/* package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping
    public	ResponseEntity<TopicDto> create(@RequestBody TopicDto dto) {
        return ResponseEntity.ok(topicService.createTopic(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAll() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }
} */

