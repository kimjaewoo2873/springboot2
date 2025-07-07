package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> { //JpaRepository는 CRUD작업,페이지처리 작업,정렬 작업
    // 네이티브 쿼리 메소드는 SQL쿼리를 리파지터리 메소드로 실행할 수 있게 해줌
    // 특정 게시글의 모든 댓글 조회, 조건문 매개변수 앞에 :(콜론) 붙여해줘야함
    @Query(value="SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // @Query(value="SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)도 가능
    // 특정 닉네임의 모든 댓글 조회 , 네이티브 쿼리 XML 사용, 경로 : META-INF > orm.xml
    List<Comment> findByNickname(String nickname);
}
