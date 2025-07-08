package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;
    public List<PizzaDto> getList() {
        // 엔터티 -> DTO변환
        List<Pizza> entityList = pizzaRepository.findPizza(); // pizza 엔티티 다 조회
        List<PizzaDto> dtoList = new ArrayList<PizzaDto>(); // 빈 리스트 생성
        for(int i=0;i<entityList.size(); i++) {
            Pizza p = entityList.get(i);
            PizzaDto pizzaDto = PizzaDto.createPizzaDto(p);
            dtoList.add(pizzaDto);
        }
        return dtoList;
    }
}
