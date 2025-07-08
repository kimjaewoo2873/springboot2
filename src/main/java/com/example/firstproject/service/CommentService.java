package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    // 서비스와 함께 협업할 리퍼지터리 객체 주입
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리퍼지터리가 있어야 댓글 생성 시, 대상 게시글의 존재 여부를 파악할 수 있음

    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔터티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>(); // CommentDto를 저장하는 빈 ArrayList 생성
        for (int i=0;i<comments.size(); i++) {
            Comment c = comments.get(i); // 엔터티 목록에서 엔터티를 하나씩 꺼냄
            CommentDto dto = CommentDto.createCommentDto(c); // 댓글 엔터티를 DTO로 변환
            dtos.add(dto); // dtos 리스트에 삽입
        }
        // 3. 결과 반환
        return dtos;
        */

        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream() // 댓글 엔터티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) // 엔터티를 DTO로 매핑
                .collect(Collectors.toList()); // 스트림 데이터를 리스트 자료형으로 변환
    }
    @Transactional // create()메서드는 DB내용을 바꾸기 때문에 실패할 경우 대비 트랜잭션 추가 설정
    public CommentDto create(Long articleId, CommentDto dto) {
        // DB에서 부모 게시글을 조회해 가져오고 없을 경우 예외 발생시키기
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                "대상 게시글이 없습니다."));
        // 부모 게시글의 새 댓글 엔터티 생성하기
        Comment comment = Comment.createComent(article, dto);
        // 생성된 엔티티를 DB에 저장하기
        Comment created = commentRepository.save(comment);
        // DB에 저장한 엔티티를 DTO로 변환해 반환하기
        return CommentDto.createCommentDto(created);
    }
    
    @Transactional // 데이터 수정하기에, 롤백 가능하게 트랜잭션으로 설정
    public CommentDto update(Long id, CommentDto dto) { // id는 수정 대상 댓글의 id임
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
                "대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔터티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
                "대상이 없습니다."));
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
