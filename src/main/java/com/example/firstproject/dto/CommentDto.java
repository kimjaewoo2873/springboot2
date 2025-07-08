package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CommentDto {
    // Comment의 구조와 똑같게 선언해야함
    private Long id; // 댓글의 id
    private Long articleId; // 댓글의 부모(게시물)의 id
    private String nickname; // 댯글 작성자
    private String body;  // 댓글 본문

    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(c.getId(), c.getArticle().getId(), c.getNickname(), c.getBody());
                         //  댓글 엔터티id, 부모 게시글id, 댓글 엔티터nickname, 댓글 엔터티body
    }
}
