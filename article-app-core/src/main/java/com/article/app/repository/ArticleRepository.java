package com.article.app.repository;

import com.article.app.domain.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jagwani on 11/3/2015.
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article,Long> {


    Article findById(Long id);
    Article findTopByOrderByIdDesc();
    List<Article> findAllByAuthor(String author);
    List<Article> findAllByPublishtxBetween(Date from, Date to);
    List<Article> findAllByKeywords(String keywords);
    Article save(Article article);
    void deleteById(Long id);



}
