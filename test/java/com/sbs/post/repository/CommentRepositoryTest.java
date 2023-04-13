package com.sbs.post.repository;

import com.sbs.post.entity.Article;
import com.sbs.post.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 조회 테스트")
    @Transactional
    void findByArticleId() {
        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(1L, "테스트1", "테스트중", "나");
            Comment e1 = new Comment(1L,article,"나다","댓글1");
            Comment e2 = new Comment(2L,article,"내다","댓글2");

            List<Comment> expected = Arrays.asList(e1,e2);
            assertEquals(expected.toString(), comments.toString(), "1번 글의 모든 댓글 출력");
        }
    }

}