package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 필드 포함해서 매개변수있는 생성자 만들어줌
@NoArgsConstructor // 기본 생성자 만들어줌
@ToString
@Entity // DB가 해당 객체를 인식하도록 엔터티 생성, 해당 클래스로 테이블을 만들어라
@Getter
public class Article {
    @Id // 엔터티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;

    @Column // title 필드 선언, DB테이블의 title 열과 연결됨
    private String title;

    @Column // content 필드 선언, DB테이블의 content 열과 연결됨
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
