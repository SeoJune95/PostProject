package com.sbs.post.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbs.post.entity.Comment;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class CommentForm {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String editor;
    private String content;

    @JsonCreator
    public CommentForm(@JsonProperty("id") Long id,
                       @JsonProperty("article") Long articleId,
                       @JsonProperty("editor") String editor,
                       @JsonProperty("content") String content) {
        this.id = id;
        this.articleId = articleId;
        this.editor = editor;
        this.content = content;
    }




    public static CommentForm createCommentDto(Comment comment) {
        return new CommentForm(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getEditor(),
                comment.getContent()
        );
    }

}
