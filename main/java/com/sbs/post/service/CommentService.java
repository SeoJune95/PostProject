package com.sbs.post.service;


import com.sbs.post.dto.CommentForm;
import com.sbs.post.entity.Article;
import com.sbs.post.entity.Comment;
import com.sbs.post.repository.ArticleRepository;
import com.sbs.post.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.RequestEntity.patch;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;



    // 해당 글의 전체 댓글 조회
    public List<CommentForm> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentForm.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    public List<Comment> commentList(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }


    @Transactional
    public CommentForm create(Long articleId, CommentForm dto) {
        log.info("articleId : " + articleId);
        log.info("DTO : " + dto.toString());
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalStateException("댓글 생성에 실패 하였습니다"));
        log.info("article : " + article.toString());
        Comment comment = Comment.createComment(dto, article);
        Comment created = commentRepository.save(comment);
//        log.info("article : " + article.toString());
//        log.info("comment : " + comment.toString());
//        log.info("created : " + created.toString());

        return CommentForm.createCommentDto(created);
    }

    @Transactional
    public CommentForm update(Long id, CommentForm dto){
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("댓글 수정에 실패하였습니다. 대상 댓글이 없습니다."));
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentForm.createCommentDto(updated);
    }

    public CommentForm delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("댓글 삭제 실패. 삭제할 댓글이 없습니다."));
        commentRepository.delete(target);
        return CommentForm.createCommentDto(target);
    }

    public void changeByArticleId(Long id) {
        commentRepository.changeByArticleId(id);
    }

    //    // 해당 글의 댓글 생성
//    public Comment create(CommentForm dto, Long id) {
//        Comment comment = dto.toEntity();
//        if (id == null) {
//            return null;
//        }
//        comment.setArticle(articleRepository.findById(id).orElse(null));
//        return commentRepository.save(comment);
//    }
//
//    // 해당 글의 댓글 수정
//    public Comment update(CommentForm dto, Long id) {
//        Comment comment = dto.toEntity();
//        Comment target = commentRepository.findById(id).orElse(null);
//        log.info("comment : " + comment + "    " + " target : " + target);
//        target.patch(comment);
//        Comment updated = commentRepository.save(target);
//        updated.setArticle(articleRepository.findById(id).orElse(null));
//        return updated;
//    }
//
//    public Comment delete(Long id) {
//        Comment target = commentRepository.findById(id).orElse(null);
//
//        if (target == null) {
//            return null;
//        }
//        commentRepository.delete(target);
//        return target;
//    }
}
