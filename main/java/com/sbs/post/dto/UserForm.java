package com.sbs.post.dto;

import com.sbs.post.entity.Users;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    private Long id;
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;

    public Users toEntity() {
        return new Users(id,userId,userName,userPassword,userEmail);
    }

}
