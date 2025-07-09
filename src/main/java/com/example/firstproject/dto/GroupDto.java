package com.example.firstproject.dto;

import com.example.firstproject.entity.Group;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Long groupId;
    private String groupName;

    public static GroupDto createDto(Group g) {
        return new GroupDto(g.getGroupId(), g.getGroupName());
    }
}
