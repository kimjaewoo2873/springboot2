package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll(); // 데이터는 리퍼지토리를 통해 가져옴
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); // dto -> 엔터티로 변환환 article에 저장
        if(article.getId() != null)
            return null;
        return articleRepository.save(article); // article을 DB에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity(); // 수정할 데이터, dto -> 엔터티로 변환
        Article target = articleRepository.findById(id).orElse(null); // 기존 데이터 찾기
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null; // 응답은 컨트롤러가 하므로, 오류시 그냥 null 반환
        }
        // 기존 데이터 수정하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated; // 응답은 컨트롤러가 하므로, 수정 데이터만 반환
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리하기
        if(target == null)
            return null;
        // 대상 삭제하기
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔터티 묶음으로 변환하기
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        // 2. 엔터티 묶음을 DB에 저장하기
        articleList.stream().forEach(article -> articleRepository.save(article));
        // 3. 강제로 예외 발생시키기
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        return articleList;
    }
}
