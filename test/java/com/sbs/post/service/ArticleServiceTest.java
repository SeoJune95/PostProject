package com.sbs.post.Service;

import com.sbs.post.dto.ArticleForm;
import com.sbs.post.entity.Article;
import com.sbs.post.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 부트와 통합해서 테스트 가능.
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @DisplayName("전체리스트 테스트")
    @Test
    void index() {
        Article a1 = new Article(1L, "우리나라만세", "우크라이나힘내", "아아아");
        Article a2 = new Article(2L, "가나다라", "아자차카", "이이이");
        Article a3 = new Article(3L, "아아아아아", "노온오ㅗ오", "오오오");
        List<Article> expected = new ArrayList<>(Arrays.asList(a1, a2, a3));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success_존재하는ID() {
        Long id = 1L;
        Article expected = new Article(id, "우리나라만세", "우크라이나힘내", "아아아");

        Article article = articleService.show(id);
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_fail_존재하지않는ID(){
        Long id=1L;
        Article expected = null;
        Article article = articleService.show(id);

        assertEquals(expected,article);
    }

    @Test
    void createTest(){
        String title = "DB에 자동 생성 ID값을 현재 알수없음.";
        String content = "ID값을 예상값으로 테스트";
        String editorName = "라라라";
        ArticleForm form = new ArticleForm(1L,title, content, editorName);

        Article expected = new Article(156L, "DB에 자동 생성 ID값을 현재 알수없음.", "ID값을 예상값으로 테스트", "라라라");
        Article article = articleService.create(form);
        assertEquals(expected.toString(),article.toString());
    }
}