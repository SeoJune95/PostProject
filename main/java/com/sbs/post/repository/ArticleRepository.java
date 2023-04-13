package com.sbs.post.repository;

import com.sbs.post.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {    // JPA를 통해서 CRUD를 수행하기 위해선 JPA에서 만들어 놓은
    //인터페이스를 상속을 받아야함. 그게 바로 CrudRepository를 상속받으면 됌. <> 안에는 아티클 클래스에 Long 타입을 받아서..blabla

    //List<Article> articleEntityList = articleRepository.findAll(); 을 사용하려면 형변환 override를 해줘야함.
    // 그래야만 다음에 findAll() 을 List 형식으로 쓸때 편함.
    @Override
    ArrayList<Article> findAll();
    ArrayList<Article> findByTitle(String keyword);
}

//TODO: JpaRepasitory 사용시 주의할점.
// 1. 기본형의 경우, 래퍼 클래스를 지정한다는 점.
// 2. 클래스의 선언 앞에 @Repasitory 어노테이션을 붙혀줘야 JPA임을 알 수있다.
// 3. @Query - SQL과 유사한 JPQL (Java Persistence Query Language) 라는 객체지향 쿼리 언어를 통해 복잡한 쿼리 처리를 지원
