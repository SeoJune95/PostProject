package com.sbs.post.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbs.post.entity.Article;
import lombok.ToString;


@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private String editorName;

    @JsonCreator
    public ArticleForm(@JsonProperty("id") Long id,
                       @JsonProperty("title") String title,
                       @JsonProperty("content") String content,
                       @JsonProperty("editorName") String editorName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.editorName = editorName;
    }

//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity(){
        return new Article(id,title,content,editorName);
    }

}
