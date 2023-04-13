package com.sbs.post.entity;


import com.sbs.post.dto.CommentForm;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id")   // 외래키지정 하는 방법.
    private Article article;
    @Column
    private String editor;
    @Column
    private String content;

    public static Comment createComment(CommentForm dto, Article article) {
        log.info("받아온 dto :  " + dto.toString());
        log.info("받아온 article :  " + article.toString());
        if(dto.getId() != null)
            throw new IllegalStateException("댓글 생성에 실패하였습니다. 댓글의 ID가 있어야 합니다.");
        if (dto.getArticleId() != article.getId()) {
            throw new IllegalStateException("댓글 생성에 실패하였습니다. 게시글의 ID가 잘못 되었습니다.");
        }
        return new Comment(
                dto.getId(),article,dto.getEditor(),dto.getContent()
        );
    }


//    public Comment(Long id,String content, Article article){
//        this.id = id;
//        this.content = content;
//        this.article = article;
//    }

    public void patch(CommentForm dto) {
        if(this.id != dto.getId()) {
            throw new IllegalStateException("댓글 수정에 실패 하였습니다. 대상 또는 댓글이 없습니다.");
        }
        if(dto.getEditor() != null){
            this.editor = dto.getEditor();
        }
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }
    }
}
