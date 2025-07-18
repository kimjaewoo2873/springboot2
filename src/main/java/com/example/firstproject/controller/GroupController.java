package com.example.firstproject.controller;

import com.example.firstproject.dto.GroupDto;
import com.example.firstproject.dto.TeamDto;
import com.example.firstproject.service.GroupService;
import com.example.firstproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/groups/{groupId}")
    public String show(@PathVariable Long groupId, Model model) {
        GroupDto groupDto = groupService.getGroup(groupId);
        List<TeamDto> teamDtos = teamService.getTeamsOnGroup(groupId);
        model.addAttribute("groupname",groupDto.getGroupName());
        model.addAttribute("teamDtoList", teamDtos);
        return "groups/show";
    }
}
