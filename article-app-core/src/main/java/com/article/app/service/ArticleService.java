package com.article.app.service;

import com.article.app.domain.Article;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jagwani on 11/4/2015.
 */
public interface ArticleService {

    Article findById(Long id);
    Article findArticle();
    List<Article> findAllArticleByAuthor(String author);
    List<Article> findAllArticleByPublishDate(Date startDate, Date endDate);
    List<Article> findAllArticleByKeywords(String keywords);
    Article saveArticle(Article article);
    void deleteById(Long id);

}
