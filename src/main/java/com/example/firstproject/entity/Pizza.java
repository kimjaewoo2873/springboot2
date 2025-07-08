package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID 순서대로 입력
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public static Pizza createPizza(PizzaDto dto) {
        if(dto.getId() != null)
            throw new IllegalArgumentException("피자 생성 실패!, id 입력하면 안됌");
        return new Pizza(dto.getId(), dto.getName(), dto.getPrice()); // 근데 id는 null이긴함, id는 DB가 입력함
    }

    public void patch(PizzaDto dto) {
        // 예외 처리
        if(this.id != dto.getId())
            throw new IllegalArgumentException("잘못된 id, 일치하지 않음");
        // 갱신
        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getPrice() != null)
            this.price = dto.getPrice();
    }
}
