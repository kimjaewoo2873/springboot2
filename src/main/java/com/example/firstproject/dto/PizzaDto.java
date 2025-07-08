package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaDto {
    private Long id;
    private String name;
    private String price;
    public static PizzaDto createPizzaDto(Pizza p) {
        return new PizzaDto(p.getId(), p.getName(), p.getPrice());
    }
}
