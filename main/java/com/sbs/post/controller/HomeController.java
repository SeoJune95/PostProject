package com.sbs.post.controller;

import com.sbs.post.entity.Article;
import com.sbs.post.entity.Users;
import com.sbs.post.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    ArticleService articleService;
    @GetMapping("/articles")
    public String showLists(Model model,HttpSession session) {
        List<Article> list = articleService.index();
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "/articles/index";
    }
}
