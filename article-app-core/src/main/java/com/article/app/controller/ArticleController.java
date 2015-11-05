package com.article.app.controller;

import com.article.app.domain.Article;
import com.article.app.facade.ArticleFacade;
import com.article.app.repository.ArticleRepository;
import com.article.app.service.ArticleService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


/**
 * Created by Jagwani on 11/3/2015.
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    @NonNull
    private ArticleFacade articleFacade;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Article findArticle() {
        LOGGER.info("findArticle ");
        return articleFacade.findArticle();
    }

    @RequestMapping(method = RequestMethod.GET, params = "id" , produces = "application/json")
    public Article findArticleById(@RequestParam(value = "id") long id) {
        LOGGER.info("findArticleById ", id);
        return articleFacade.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, params = "author", produces = "application/json")
    public List<Article> findAllArticleByAuthor(@RequestParam(value = "author") String author) {
        LOGGER.info("findAllArticleByAuthor ", author);
        return articleFacade.findAllArticleByAuthor(author);
    }

    @RequestMapping(method = RequestMethod.GET, params = "{startdate, enddate}", produces = "application/json")
    public List<Article> findAllArticleByPublishDate(@RequestParam(value = "startdate") Date startdate, @RequestParam(value = "enddate") Date enddate) {
        LOGGER.info("findAllArticleByPublishDate ", startdate);
        return articleFacade.findAllArticleByPublishDate(startdate, enddate);
    }

    @RequestMapping(method = RequestMethod.GET, params = "keywords", produces = "application/json")
    public List<Article> findAllArticleByKeywords(@RequestParam(value = "keywords") String keywords) {
        LOGGER.info("findAllArticleByKeywords ", keywords);
        return articleFacade.findAllArticleByKeywords(keywords);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Article> createArticle(@RequestBody Article article) throws Exception{
        LOGGER.info("Creating Article ", article.getAuthor());
        articleFacade.saveArticle(article);
        return new ResponseEntity<Article>(article,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE )
    public ResponseEntity<Article> deleteArticle(@RequestParam(value = "id") long id) {
        LOGGER.info("Deleting Article with id {}", id);
        articleFacade.deleteById(id);
        return new ResponseEntity<Article>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Article> updateArticle(@RequestParam(value = "id") long id, @RequestBody Article article) {
        LOGGER.info("Updating Article ", id);
        Article currentArticle = articleFacade.findById(id);
        if (currentArticle == null) {
            LOGGER.info("Article currentArticle = articleFacade.findById(id); with id {}" , id , " not found");
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }
        currentArticle.setDescription(article.getDescription());
        currentArticle.setAuthor(article.getAuthor());
        currentArticle.setHeader(article.getHeader());
        currentArticle.setKeywords(article.getKeywords());
        currentArticle.setPublishtx(article.getPublishtx());
        currentArticle.setText(article.getText());
        articleFacade.saveArticle(currentArticle);
        return new ResponseEntity<Article>(currentArticle, HttpStatus.OK);
    }

}
