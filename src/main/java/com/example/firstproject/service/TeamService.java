package com.example.firstproject.service;

import com.example.firstproject.dto.TeamDto;
import com.example.firstproject.entity.Group;
import com.example.firstproject.entity.Team;
import com.example.firstproject.repository.GroupRepository;
import com.example.firstproject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    TeamRepository teamRepository;

    public List<TeamDto> getTeamsOnGroup(Long groupId) {
       List<Team> teamList = teamRepository.findByTeam(groupId);

    }
}
