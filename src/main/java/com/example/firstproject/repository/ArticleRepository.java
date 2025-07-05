package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// 관리 대상 엔터티 클래스 타입, 대푯값 타입
public interface ArticleRepository extends CrudRepository<Article, Long> { // CrudRepository 인터페이스 상속
    @Override
    ArrayList<Article> findAll(); // 캐스팅 대신 오버라이딩
}