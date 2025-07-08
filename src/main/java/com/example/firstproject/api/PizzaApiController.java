package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    PizzaService pizzaService;
    // GET
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> getList() {
        // DTO 목록 얻어오기
        List<PizzaDto> pizzaList = pizzaService.getList();
        // DTO 반환
        return ResponseEntity.status(HttpStatus.OK).body(pizzaList);
    }
    // POST
    // PATCH
    // DELETE
}
