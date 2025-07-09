package com.example.firstproject.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamDto {
    private Long id;
    private String groupId;
    private String country;
}
