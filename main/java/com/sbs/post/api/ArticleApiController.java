package com.sbs.post.api;

import com.sbs.post.dto.ArticleForm;
import com.sbs.post.entity.Article;
import com.sbs.post.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    //전체 조회
    @GetMapping("/api/articles")
    public List<Article> allIndex(){
        return articleService.index();
    }
//
//    //한개씩 보기
    @GetMapping("/api/articles/show/{id}")
    public Article detailIndex(@PathVariable Long id){
        return articleService.show(id);
    }
//
//    //게시글 생성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> createIndex(@RequestBody ArticleForm form){
        log.info(form.toString());
        Article created = articleService.apiCreate(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    //게시글 수정
//
//    // 예외처리를 위한 예외핸들러
////    @ExceptionHandler(HttpMessageNotReadableException.class)
////    public ResponseEntity<String> errMsg(HttpMessageNotReadableException ex) {
////        return ResponseEntity.badRequest().body("Invalid request body: " + ex.getMessage());
////    }
////    @PostMapping("/api/articles/update/{id}")
////    public Article updateIndex(@RequestBody ArticleForm form,@PathVariable Long id){
////        Article article = form.toEntity();
//////        log.info(article.toString());
////        article.setId(id);
////        log.info("파라미터 id 추가 : " + article.toString());
////        if (articleRepository.existsById(id) && article.getTitle() != null && article.getContent() != null){
////            return articleRepository.save(article);
////        }
////        throw new HttpMessageNotReadableException("Error!");
////    }
//
//
    //수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,@RequestBody ArticleForm form){
        Article updated = articleService.update(id,form);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
////
////
////    //삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> deleteIndex(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // 트랜잭션 테스트
    @PostMapping("/api/articles/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> forms) {
        List<Article> createList = articleService.createArticles(forms);
        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
