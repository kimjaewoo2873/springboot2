package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired //스프링이 객체를 대신 생성해서 자동으로 넣어주는 기능
    ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 2. 실제 데이터 작성
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력(){
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected, article); // 존재하지 않는 데이터는 null 반환
    }

    @Transactional // 조회하는 테스트를 제외하고 데이터를 생성, 수정, 삭제하는 테스트를 할 때는 트랜잭션으로 묶어, 테스트 종료 후 원상복귀(롤백)
    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        // 예상값이 DTO에 영향을 안 줘도, 서비스가 DTO를 어떻게 처리하는지 검증하는 게 핵심
        ArticleForm dto = new ArticleForm(null, title, content); // dto 생성
        Article expected = new Article(4L, title, content);
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article); // 실제, 예상 데이터 값 null은 toString() 메서드를 호출할 수 없습니다.
    }

    @Transactional
    @Test
    void update_성공_존재하는_id의_title_content가_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 2L;
        String title = "나나나나";
        String content = "2222";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 2. 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        String title = "AAAA";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "AAAA", "1111");
        // 2. 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {
        Long id = 4L;
        String title = "다다다다";
        String content = "3333";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null; // 존재하지 않는 id이므로 데이터 없음

        Article article = articleService.update(id, dto);

        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void delete_성공_존재하는_id_입력() {
        Long id = 2L;
        Article expected = new Article(id, "나나나나", "2222");

        Article article = articleService.delete(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Transactional
    @Test
    void delete_실패_존재하지_않는_id_입력() {
        Long id = 12L;
        Article expected = null;

        Article article = articleService.delete(id);

        assertEquals(expected, article);
    }
}