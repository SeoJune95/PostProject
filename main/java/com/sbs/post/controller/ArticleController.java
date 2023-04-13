package com.sbs.post.controller;

import com.sbs.post.dto.ArticleForm;
import com.sbs.post.dto.CommentForm;
import com.sbs.post.entity.Article;
import com.sbs.post.entity.Users;
import com.sbs.post.repository.ArticleRepository;
import com.sbs.post.service.ArticleService;
import com.sbs.post.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller     // 컨트롤러라고 지정.
@Slf4j
public class ArticleController {

    @Autowired      // 객체 주입..? 이게 DI임. 객체를 내가 만들어야하는데 자동으로 생성해주는 어노테이션.
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    //TODO: articles/new 는 게시판을 등록폼이 있는 페이지.
    //      해당 url이 들어오면 articles/new.mustache 를 띄워줌.
    @GetMapping("articles/new")
    public String newDtoForm(Model model, HttpServletRequest request) {
        List<Article> showListBriefly = articleService.index();
        HttpSession session = request.getSession();
        Users nowUser = (Users) session.getAttribute("user");
        model.addAttribute("list", showListBriefly);
        model.addAttribute("user", nowUser);
        if(nowUser == null){
            return "/user/dologin";
        }
        return "articles/new";
    }


    //TODO : new 안의 게시글등록 확인버튼을 누르면 form의 action은 /articles/create/ 이므로
    //      해당 액션에 대한 Post로 DB에 저장.
    //      20230222 추가 - (redirect) RedirectAttributes 를 통해 확인 버튼을 누르면 result 페이지를 띄워줌.
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form, RedirectAttributes rtts,HttpServletRequest request) {
        //TODO:레파지토리에 저장
        Article saved = articleService.create(form);
        System.out.println("saved : "+saved);

        //TODO : msg 이부분은 요청이 성공하고 result 페이지를 띄울때 "등록완료!!!" 문구를 view파일에서 사용할 수 있음.
        rtts.addFlashAttribute("msg", "등록완료!");
        return "redirect:/result/"+saved.getId();
    }


    //TODO : 20230222 - new 페이지의 등록확인버튼을 누르면 redirect/result/article.getId() 으로 return.
    //      redirect 로 인해 등록의 결과 페이지를 반환.
    @GetMapping("/result/{id}")
    public String result(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Article entity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", entity);
        return "/articles/result";
    }


    //TODO: 게시글 목록 클릭시 게시글 상세페이지로 이동.
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Article entity = articleRepository.findById(id).orElse(null);
        // 로그인이 되어있는 사람과 게시글 작성자가 다른지 구별하기 위해.
        boolean isSameUser = false;
        // try / catch가 없으면 게시글의 작성자와 로그인이 되어있는 사람이 다르면 NullPointerException 를 발생.
        // 그러므로 try / catch 문으로 감싸서 오류가 발생하지 않게 하기위함.
        try {
            // 현재 session에 로그인 되어있는 사람의 name 과 작성자의 name 이 같을 경우 삭제, 수정 버튼 활성화.
            Users nowUser = (Users) session.getAttribute("user");
            String nowUserName = nowUser.getUserName();
            model.addAttribute("user", session);
            if(entity.getEditorName().equals(nowUserName)) {
                isSameUser = true;
            };
        } catch(NullPointerException e){
            // 다를경우.
            System.out.println("로그인이 되어있는 사람과 작성자가 다릅니다.");
        }
        model.addAttribute("isSameUser", isSameUser);
        model.addAttribute("article", entity);
        List<CommentForm> comments = commentService.comments(id);
        model.addAttribute("commentDtos",comments);
        return "/articles/show";
    }


//    TODO : Navvar 안에 search 에 id값 입력시 상세페이지로 이동.(진행중)
    @GetMapping("/articles/search/{keyword}")
    public String searchShow(@PathVariable String keyword, Model model) {
        log.info("keyword = " + keyword);
//        Article entity = articleRepository.findById(id).orElse(null);
//        model.addAttribute("article", entity);
        return "/articles/search";
    }


    //TODO : 등록된 게시글의 리스트를 보여주는 페이지.
    @GetMapping("/articles/list")
    public String showList(Model model,HttpSession session) {
        List<Article> list = articleRepository.findAll();
        log.info(list.toString());
        model.addAttribute("list", list);
        model.addAttribute("user",session.getAttribute("user"));
        return "/articles/borderlist";
    }


    //TODO : url /articles 입력시 /articles/index 반환. 게시글 리스트.



    //TODO : 게시글 수정을 위한 컨트롤러. GetMapping을써서 해당 게시물의 id 값을 파라미터로 던진고
//            update-form 안의 title과 content에 수정할 게시물의 title과 content를 가져온다.
    @GetMapping("/articles/update-form/{id}")
    public String articleUpdateForm(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "/articles/update-form";
    }

//    @GetMapping("/articles/update-form/{id}")
//    public String articleUpdateForm(@RequestParam("id") Article article, Model model) {
//        model.addAttribute("article", articleEntity);
//        return "/articles/update-form";
//    }

    //TODO : articleUpdateForm에서 가져온 id에 대한 title과 content를 변경한다.
    //      해당 id 값의 title 과 content 를 수정.
    @PostMapping("/articles/update-article")
    public String articleUpdate(ArticleForm form, Model model,HttpSession session) {
        //현재 로그인된 사용자 세션
        Users user = (Users)session.getAttribute("user");
        //DTO 생성.
        Article articleEntity = form.toEntity();
        articleEntity.setEditorName(user.getUserName());
        log.info("DTO : " + articleEntity.toString());

        //DTO를 Entity로 변환
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        log.info("Entity : " + target.toString());
//         예외처리 target이 null일 경우 아무것도 안됌.
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        // redirect로 articles/{id} 로 리다이렉트해줌.
        return "redirect:/articles/" + articleEntity.getId();
    }


    @GetMapping("/articles/delete/{id}")
    public String articleDeleteForm(ArticleForm form,RedirectAttributes rttr) {
        Article article = form.toEntity();
//        log.info("article : " + article.toString());
        Article target = articleRepository.findById(article.getId()).orElse(null);
        rttr.addAttribute("id",article.getId());
//        log.info("article id : " + article.getId());
        return "redirect:/deleteRedirect";
    }

    @GetMapping("/deleteRedirect")
    public String articleDelete(@RequestParam Long id,RedirectAttributes rttr){
        log.info("id : " + id);
        commentService.changeByArticleId(id);
        articleRepository.deleteById(id);
        rttr.addAttribute("msg","삭제가 완료되었습니다.");
        return "redirect:/articles";
    }
}