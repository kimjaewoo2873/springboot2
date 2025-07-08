package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    public static Comment createComent(Article article, CommentDto dto) { // article은 부모 게시물 객체 받아온거임
        if(dto.getId() != null) // 댓글 id가 이미 있는 경우, 엔터티의 id는 DB가 자동 생성하므로, 값이 없어야함
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(!Objects.equals(dto.getArticleId(), article.getId())) // 부모 게시글과 엔터티에서 가져온 부모 게시글의 id가 다르면 안됌
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}

