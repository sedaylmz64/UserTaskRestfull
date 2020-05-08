package com.example.deneme.service;

import com.example.deneme.controller.request.CreateTeamRequest;
import org.springframework.stereotype.Component;

@Component
public interface TeamService {
    void createTeam(CreateTeamRequest request);
}
