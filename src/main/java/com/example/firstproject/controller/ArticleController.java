
package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ArticleController {
    // log 기능 안돼서, 걍 log 선언함
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired // 스프링 부트가 미리 생성해 놓은 repository 객체 주입, 스프링 부트에선 객체 생성x
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼 데이터를 매개변수로 DTO로 받기
        log.info(form.toString());
        // System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO를 엔터티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        // System.out.println(article.toString());

        // 2. repository로 entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        // System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId(); // 리다이렉트
    }

    @GetMapping("/articles/{id}")           // 컨트롤러가 URL 요청을 받음
    public String show(@PathVariable Long id, Model model) { // @PathVariable, 매개변수로 id 받아오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id); // 댓글목록 List<CommentDto>타입으로 가져오기
        log.info(commentsDtos.toString());
        // 2. 모델에 데이터 등록하기 (article이라는 이름으로 articleEntity를 등록함)
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos); // 댓글 목록 모델에 등록

        // 3. 뷰 페이지 변환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. DB에서 모든 Article 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll(); // 다운 캐스팅 or 오버라이딩으로 타입 맞춤

        // 2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList", articleEntityList);

        // 3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit") // 데이터 조회 GET
    public String edit(@PathVariable Long id, Model model){ // 데이터 수정 페이지 요청 메소드
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update") // 데이터 생성 POST
    public String update(ArticleForm articleForm){ // 데이터 업데이트 메소드
        log.info(articleForm.toString());

        Article articleEntity = articleForm.toEntity(); // DTO를 엔터티로 변환
        log.info(articleEntity.toString());

        // DB에서 기존 데이터 가져오기, 기존 id와 수정했던거 id는 같은 id임 ,orElse(null) 못찾으면, null반환
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 기존 데이터가 있는 경우에, 기존 ID에 값을 갱신
        if(target != null) {
            articleRepository.save(articleEntity); // 엔터티를 DB에 저장(갱신)
        }
       return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");
        // 삭제할 데이터 찾기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 대상 엔터티 삭제하기
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
            //FlashAttribute는 모델에 자동 등록됨 {{#msg}} {{/msg}} 사용 가능, {{msg}}로 객체 출력
        }
        return "redirect:/articles";
    }
}
