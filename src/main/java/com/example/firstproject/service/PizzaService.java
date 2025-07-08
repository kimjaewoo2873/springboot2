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
            Pizza p = entityList.get(i); // 엔티티 목록에서 하나씩 뺌
            PizzaDto pizzaDto = PizzaDto.createPizzaDto(p); // dto로 변환
            dtoList.add(pizzaDto); // dto 목록에 넣기
        }
        return dtoList;
    }

    public PizzaDto create(PizzaDto dto) {
        Pizza pizza = Pizza.createPizza(dto); // 엔티티 생성하기
        Pizza created = pizzaRepository.save(pizza); // 리퍼지터리에 엔티티 저장
        return PizzaDto.createPizzaDto(created); // 다시 dto로 바꿔서 리턴
    }

    public PizzaDto update(Long id, PizzaDto dto) {
        // 수정할 피자 찾기
        Pizza target = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("피자를 찾을 수 없음"));
        target.patch(dto); // 새 데이터로 피자 수정하기
        Pizza updated = pizzaRepository.save(target); // 엔티티 리파지터리에 저장
        return PizzaDto.createPizzaDto(updated);
    }
}
