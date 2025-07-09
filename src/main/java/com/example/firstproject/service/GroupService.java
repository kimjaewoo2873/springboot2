package com.example.firstproject.service;

import com.example.firstproject.dto.GroupDto;
import com.example.firstproject.entity.Group;
import com.example.firstproject.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    public GroupDto getGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("ID를 찾을 수 없습니다."));
        return GroupDto.createDto(group);
    }
}
