package com.article.app.facade.impl;

import com.article.app.domain.Article;
import com.article.app.facade.ArticleFacade;
import com.article.app.service.ArticleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Jagwani on 11/4/2015.
 */
@Component
public class DefaultArticleFacade implements ArticleFacade {

    @Autowired
    private @NonNull ArticleService articleService;

    @Override
    public Article findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public Article findArticle() {
        return articleService.findArticle();
    }

    @Override
    public List<Article> findAllArticleByAuthor(String author) {
        return articleService.findAllArticleByAuthor(author);
    }

    @Override
    public List<Article> findAllArticleByPublishDate(Date startDate, Date endDate) {
        return articleService.findAllArticleByPublishDate(startDate, endDate);
    }

    @Override
    public List<Article> findAllArticleByKeywords(String keywords) {
        return articleService.findAllArticleByKeywords(keywords);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleService.saveArticle(article);
    }

    @Override
    public void deleteById(Long id) {
        articleService.deleteById(id);
    }
}
