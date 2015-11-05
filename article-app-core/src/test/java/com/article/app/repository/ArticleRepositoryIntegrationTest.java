package com.article.app.repository;

import com.article.app.domain.Article;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


/**
 * Created by Jagwani on 11/4/2015.
 */
public class ArticleRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void testFindArticle() {

        Article article = this.articleRepository.findTopByOrderByIdDesc();
        Assert.assertNotNull(article);
    }

    @Test
    public void testfindAllByAuthor() {
        List<Article> articleList = this.articleRepository.findAllByAuthor("vinod");
        Assert.assertThat(articleList, is(not(emptyIterable())));
    }

    @Test
    public void testfindByPublishDate() {
        List<Article> articleList = this.articleRepository.findAllByPublishtxBetween(new Date(), new Date());
        Assert.assertThat(articleList,  is(emptyIterable()));
    }

    @Test
    public void testfindByKeywords() {
        List<Article> articleList = this.articleRepository.findAllByKeywords("abc");
        Assert.assertThat(articleList,  is(not(emptyIterable())));
    }

    @Test
    public void testSaveArticle() {
        Article article = new Article();
        article.setText("this is test");
        article.setAuthor("VINOD");
        article.setDescription("adffds");
        Article savedArticle =  this.articleRepository.save(article);
        Assert.assertEquals(savedArticle.getText(), article.getText());
    }

    @Test
    public void testDeleteArticle() {
        this.articleRepository.deleteById(5L);
        Assert.assertNull(this.articleRepository.findById(5L));
    }
}
