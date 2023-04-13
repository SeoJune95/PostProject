package com.sbs.post.controller;

import com.sbs.post.entity.Users;
import com.sbs.post.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("user/login")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("user/dologin")
    public String doLogin(){
        return "user/dolongin";
    }

    @PostMapping("user/login")
    public String login(@RequestParam String userId, @RequestParam String userPassword, HttpSession session,Model model) {

        Users user = userService.findByUserIdAndUserPassword(userId,userPassword);

        if(user == null) {
            return "user/login-fail";
        }

        session.setAttribute("user",user);
        return "redirect:/articles";
    }


    @GetMapping("user/logout")
    public String userLogout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/user/login";
    }

}
