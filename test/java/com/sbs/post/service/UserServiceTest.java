package com.sbs.post.Service;

import com.sbs.post.entity.Users;
import com.sbs.post.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class UserServiceTest {

    private final UserRepository userRepository;

    UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void join(Users user){
        validateDuplicateMember(user);
        userRepository.save(user);
    }
    @Test
    private void validateDuplicateMember(Users user){
        List<Users> allUsers = new ArrayList<>();
        allUsers = userRepository.findAll();
        for(Users users : allUsers){
            System.out.println(allUsers.toString());
        }
    }
}