package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Article;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a JOIN FETCH a.author")
    List<Article> findAllWithAuthor(Sort sort);

}
