package com.article.app.controller;

import com.article.app.repository.AbstractIntegrationTest;
import com.article.app.starter.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jagwani on 11/5/2015.
 */

@WebAppConfiguration
public class ArticleRestControllerTest extends AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void findArticleById() throws Exception {
        this.mvc
                .perform(get("/api/article").param("1d", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("header", is("4")))
                .andExpect(jsonPath("description", is("Brisbane")));
    }

    @Test
    public void testFindAllArticleByAuthor() throws Exception {
        this.mvc
                .perform(get("/api/article").param("author", "vinod")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void findAllArticleByKeywords() throws Exception {
        this.mvc
                .perform(get("/api/article").param("keywords", "abc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].description", is("Brisbane")));
    }

    @Test
    public void deleteArticle() throws Exception {
        this.mvc
                .perform(delete("/api/article/delete").param("id", "5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateArticle() throws Exception {
        this.mvc
                .perform(put("/api/article/update").param("id", "5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"header\":\"id1\",\"description\":\"description\", \"author\":\"chand1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("description")));
    }

    @Test
    public void insertArticle() throws Exception {
        this.mvc
                .perform(post("/api/article/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"header\":\"id1\",\"description\":\"description\", \"author\":\"vinodjagwani\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author", is("vinodjagwani")));
    }
}
