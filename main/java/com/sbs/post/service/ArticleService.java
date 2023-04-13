package com.sbs.post.service;

import com.sbs.post.dto.ArticleForm;
import com.sbs.post.entity.Article;
import com.sbs.post.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article apiCreate(ArticleForm form) {
        Article article = form.toEntity();
        if (article.getId() == null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article create(ArticleForm form) {
        Article article = form.toEntity();
        return articleRepository.save(article);
    }
    public ArrayList<Article> findByTitle(String keyword){
        return articleRepository.findByTitle(keyword);
    }


    public Article update(Long id,ArticleForm form){
        Article article = form.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()){
            log.info("잘못된 id 요청입니다. id : {} , article : {}", id, article);
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null) {
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    //트랜잭션 테스트
    @Transactional  // @Transactional 이 있어야 오류가 발생하면 rollback을 수행함.
    public List<Article> createArticles(List<ArticleForm> forms) {
        //dto의 묶음을 entity로 변환
        List<Article> articleList = forms.stream()
                .map(form -> form.toEntity())
                .collect(Collectors.toList());
        //entity를 db에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("실패 하였습니다.!.!")
        );  //TODO : 오류가 발생했을때 @Transactional을 안해주면 DB에는 값이 저장되지만 해당 어노테이션을 달면
            //       오류가 발생하면 모든 작업이 다시 rollback 된다.
        //결과값 반환
        return null;
    }

}
