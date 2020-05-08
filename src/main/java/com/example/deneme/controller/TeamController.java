package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateTeamRequest;
import com.example.deneme.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team")
    public void createTeam(@Valid @RequestBody CreateTeamRequest request){
        teamService.createTeam(request);
    }
}
