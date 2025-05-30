package com.openclassrooms.mddapi.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Optionnel : pour avoir tous les articles d'un topic
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Article> articles;

    public Topic() {}

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }
}
