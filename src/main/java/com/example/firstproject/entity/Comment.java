package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 증가
    private Long id; // 대표키

    @ManyToOne // Comment 엔터티와 Article 엔터티를 다대일 관계로 설정
    @JoinColumn(name="article_id") // 외래키 생성, Article 엔터티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시물

    @Column
    private String nickname; //  댓글을 단 사람

    @Column
    private String body; // 댓글 본문

    public static Comment create(Article article, CommentDto dto) { // article은 부모 게시물 객체 받아온거임
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다."); // 댓글 id가 이미 있는 경우
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }
}

