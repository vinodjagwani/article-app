package com.article.app.facade;

import com.article.app.domain.Article;

import java.util.Date;
import java.util.List;

/**
 * Created by Jagwani on 11/4/2015.
 */
public interface ArticleFacade {

    Article findById(Long id);
    Article findArticle();
    List<Article> findAllArticleByAuthor(String author);
    List<Article> findAllArticleByPublishDate(Date startDate, Date endDate);
    List<Article> findAllArticleByKeywords(String keywords);
    Article saveArticle(Article article);
    void deleteById(Long id);


}
