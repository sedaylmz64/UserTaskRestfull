package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateTeamRequest;
import com.example.deneme.model.converter.CreateTeamRequestConverter;
import com.example.deneme.model.entity.TeamEntity;
import com.example.deneme.repositories.TeamRepository;
import com.example.deneme.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public void createTeam(CreateTeamRequest request) {
        TeamEntity teamEntity = CreateTeamRequestConverter.convert(request);
        teamRepository.save(teamEntity);
    }
}
