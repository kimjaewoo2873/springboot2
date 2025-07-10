package com.example.firstproject.dto;

import com.example.firstproject.entity.Team;
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
    private Long groupId;
    private String country;
    private String imageUrl;

    public static TeamDto createDto(Team team) {
        return new TeamDto(team.getId(), team.getGroup().getGroupId(), team.getCountry(), team.getImageUrl());
    }
}
