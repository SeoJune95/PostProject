package com.sbs.post.repository;

import com.sbs.post.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    //    Optional<Member> findByName(String name);
    Users findByUserIdAndUserPassword(String userId, String userPassword);

}

//1. 기본형의 경우에는 래퍼 클래스를 지정합니다.
//2. 클래스를 선언하는 앞부분에 @Repository 라는 어노테이션을 붙여놓아야 JPA 라는걸 알려야함.
//3. @Query 를 사용해서 DB Query를 직접 사용할 수 있다. nativeQuery = true 를 주면 JPQL 이라는 문법을 사용할 수 있다.

//Entity 영속 => 임시로 담기 위한 공간. @Transactional 단위로 묶어서 관리합니다. 임시로 담긴 데이터는 commit 후에

//@Entity

