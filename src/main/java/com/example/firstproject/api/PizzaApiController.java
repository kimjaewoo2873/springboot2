package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    PizzaService pizzaService;
    // GET
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> getList() {
        // 서비스에 위임, DTO 목록 얻어오기
        List<PizzaDto> pizzaList = pizzaService.getList();
        // DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(pizzaList);
    }
    // POST
    @PostMapping("/api/pizza")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto) {
        // 서비스에 위임
        PizzaDto created = pizzaService.create(dto);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
    // PATCH
    @PatchMapping("/api/{id}/pizza")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        // 서비스에 위임
        PizzaDto updated = pizzaService.update(id, dto);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("api/{id}/pizza")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        PizzaDto deleted = pizzaService.delete(id);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
