package com.article.app.service.impl;

import com.article.app.domain.Article;
import com.article.app.repository.ArticleRepository;
import com.article.app.service.ArticleService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Jagwani on 11/4/2015.
 */
@Service
@Transactional
public class DefaultArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultArticleService.class);

    @Autowired
    private @NonNull ArticleRepository articleRepository;

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Article findArticle() {
        return articleRepository.findTopByOrderByIdDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAllArticleByAuthor(String author) {
        return articleRepository.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAllArticleByPublishDate(Date startDate, Date endDate) {
        return articleRepository.findAllByPublishtxBetween(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> findAllArticleByKeywords(String keywords) {
        return articleRepository.findAllByKeywords(keywords);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
