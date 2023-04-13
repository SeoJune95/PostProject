package com.sbs.post.controller;

import com.sbs.post.service.UserService;
import com.sbs.post.dto.UserForm;
import com.sbs.post.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("user/join")
    public String joinDtoForm(){
        return "user/join";
    }

    @PostMapping("user/create")
    public String createUser(UserForm form, RedirectAttributes rtts)  {
        log.info("form : "+ form.toString());

        //TODO : 엔티티 만들기
        Users user = form.toEntity();
        log.info("user : "+user.toString());

        //TODO : 레파지토리 만들고 저장하기 전에 Users에 있는 userId 값중에 중복값이 있으면
//               데이터중복 예외발생처리후 예외발생시 join-fail로 이동. 정상적이면 DB에 저장.
        try{
            Users saved = userService.create(user);
        }catch (DataIntegrityViolationException ex){
            return "user/join-fail";
        }
//        userService.join(user);
        rtts.addAttribute("user",user);
        return "redirect:result";
    }

    @GetMapping("user/result")
    public String joinResult(@RequestParam Users user, Model model){
        log.info("유져~~ : " + user.toString());
        log.info("유져~~ : " + user.toString());
        if (user.getUserId().isEmpty() || user.getUserPassword().isEmpty() || user.getUserName().isEmpty()){
            return "user/join-fail";
        }
        model.addAttribute("user",user);
        return "user/result";
    }
}
