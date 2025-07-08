package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.CommentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService; // 컨트롤러가 서비스와 협업할 수 있도록, 서비스 객체 주입
    // 1. 댓글 조회 GET
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에 위임
        // ( DTO는 입·출력용 포장 데이터고, 엔터티는 DB와 직접 연결되는 실제 데이터
        // 그래서 저장할 땐 DTO → 엔터티,
        // 응답할 땐 엔터티 → DTO로 변환함 )
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 댓글 생성 POST
    @PostMapping("/api/articles/{articleId}/comments")
     public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto); // 클라이언트에 응답
    }
    // 3. 댓글 수정 PATCH
    // 4. 댓글 삭제 DELETE
}
