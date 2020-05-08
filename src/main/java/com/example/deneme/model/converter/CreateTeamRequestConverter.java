package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateTeamRequest;
import com.example.deneme.model.entity.TeamEntity;

public class CreateTeamRequestConverter {
    public static TeamEntity convert(CreateTeamRequest request){
        TeamEntity teamEntity = new TeamEntity();

        teamEntity.setName(request.getName());
        teamEntity.setDescription(request.getDescription());

        return teamEntity;
    }
}
