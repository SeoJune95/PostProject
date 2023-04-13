package com.sbs.post.service;

import com.sbs.post.entity.Users;
import com.sbs.post.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Users create(Users user) {
        return userRepository.save(user);
    }

    public Users findByUserIdAndUserPassword(String userId, String userPassword) {
        return userRepository.findByUserIdAndUserPassword(userId,userPassword);
    }

//    public String join(Users user){
//        validateDuplicateMember(user);
//        userRepository.save(user);
//        return "user/join-fail";
//    }
//    private String validateDuplicateMember(Users user){
//        userRepository.findByName(user.getUserName())
//                .ifPresent(m->{
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//        return "user/join-fail";
//    }
}
