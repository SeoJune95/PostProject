package com.sbs.post.api;

import com.sbs.post.dto.CommentForm;
import com.sbs.post.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;


    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentForm>> comments(@PathVariable Long articleId) {
        List<CommentForm> dtos = commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentForm> create(@PathVariable Long articleId, @RequestBody CommentForm dto) {
        CommentForm createdDto = commentService.create(articleId,dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentForm> update(@PathVariable Long id, @RequestBody CommentForm dto) {
        CommentForm updateDto = commentService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }


    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentForm> delete(@PathVariable Long id){
        CommentForm deleteDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
//    //댓글 생성
//    @PostMapping("/api/{id}/comments")
//    public ResponseEntity<Comment> createComment(@RequestBody CommentForm dto, @PathVariable Long id) {
//        log.info(dto.toString());
//        Comment created = commentService.create(dto, id);
//        return (created != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(created) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    // 댓글 수정.
//    @PatchMapping("/api/{id}/comments/update")
//    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentForm dto) {
//        Comment updated = commentService.update(dto, id);
//        return (updated != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(updated) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    // 댓글 삭제
//    @DeleteMapping("/api/{id}/comments/delete")
//    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
//        Comment deleted = commentService.delete(id);
//        return (deleted != null) ?
//                ResponseEntity.status(HttpStatus.OK).build() :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
}
