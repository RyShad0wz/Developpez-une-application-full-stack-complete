package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CreateArticleRequest;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          UserRepository userRepository,
                          TopicRepository topicRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public List<ArticleDto> getAll(String direction) {
        Sort sort = Sort.by(
                "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC,
                "createdAt"
        );
        return articleRepository.findAll(sort).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto getById(Long id) {
        Article art = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article non trouvé"));
        return toDto(art);
    }

    // Correction : userId passé explicitement
    public ArticleDto create(CreateArticleRequest req, Long userId) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Auteur non trouvé"));
        Topic topic = topicRepository.findById(req.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Thème non trouvé"));

        Article art = new Article();
        art.setTitle(req.getTitle());
        art.setContent(req.getContent());
        art.setAuthor(author);
        art.setTopic(topic);
        art.setCreatedAt(LocalDateTime.now());

        art = articleRepository.save(art);
        return toDto(art);
    }

    // --- UPDATE ---
    public ArticleDto update(Long id, CreateArticleRequest req, Long userId) {
        Article art = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article non trouvé"));

        // Optionnel : on vérifie que l'utilisateur courant est bien l'auteur
        if (!art.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("Vous n’êtes pas l’auteur de cet article.");
        }

        art.setTitle(req.getTitle());
        art.setContent(req.getContent());

        // Si on souhaite permettre le changement de thème :
        if (req.getTopicId() != null && !req.getTopicId().equals(art.getTopic().getId())) {
            Topic topic = topicRepository.findById(req.getTopicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Thème non trouvé"));
            art.setTopic(topic);
        }

        art = articleRepository.save(art);
        return toDto(art);
    }

    // --- DELETE ---
    public void delete(Long id, Long userId) {
        Article art = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article non trouvé"));

        // Vérifie que l’utilisateur courant est bien l’auteur
        if (!art.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("Vous n’êtes pas l’auteur de cet article.");
        }

        articleRepository.delete(art);
    }

    private ArticleDto toDto(Article art) {
        return new ArticleDto(
                art.getId(),
                art.getTitle(),
                art.getContent(),
                art.getAuthor().getId(),
                art.getTopic().getId(),
                art.getCreatedAt()
        );
    }
}
