package com.sbs.post.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
// 이거 없이 하니까 org.springframework.orm.jpa.JpaSystemException: No default constructor for entity
// 에러가 생겼었음. 로그를 찾아보니 JPA에서는 Entity는 기본 생성자를 가지고 있어야 한다는 제약조건이 있는거같음.
// 객체와 테이블 간에 매핑
// Article 클래스니 db 에는 Article 테이블이 생성.
@Entity     // JPA가 Article 클래스를 관리해줌. 보통기본값인 class(객체)의 이름을 사용. 바꿀수 있음.
            // 만약 똑같은 클래스가 있다면 충돌하지 않도록 주의해야함. 유일성이 제일 좋음.
            // 저장할 필드(변수) final 로 선언하면 안됌.
            // 생성자가하나도 없으면 기본생성자를 만들어 줘야함. ( public Article() {} )
public class Article {  //final 클래스, enum, interface, inner 클래스는 사용할 수 없음.
    @Id // id 를 primary Key로 지정하는 어노테이션.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 넘버를 생성해주는 어노테이션. ex) 1, 2, 3, ....
    private Long id;        // DB의 키값임.
    @Column // title 이라는 속성(컬럼) 생성
    private String title;
    @Column // content 라는 속성(컬럼) 생성
    private String content;
    @Column
    private String editorName;



    public Article(Long id, String title, String content, String editorName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.editorName = editorName;
    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }
        if(article.content != null) {
            this.content = article.content;
        }
    }
}
