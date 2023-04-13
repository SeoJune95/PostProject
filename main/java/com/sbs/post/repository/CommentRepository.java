package com.sbs.post.repository;

import com.sbs.post.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "select * from comment where article_id = :articleId",nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    @Modifying
    @Transactional
    @Query(value = "update comment set article_id=null where article_id = :articleId",nativeQuery = true)
    void changeByArticleId(@Param("articleId") Long articleId);

}
