package com.sbs.post.controller;

import com.sbs.post.dto.ArticleForm;
import com.sbs.post.entity.Article;
import com.sbs.post.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ArticleControllerTest {
    @Autowired
    private ArticleRepository articleRepository;



    @Test
    void articleSave(ArticleForm form){
        form = new ArticleForm(null,"test","bb","cc");
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        System.out.println(saved);

    }


}