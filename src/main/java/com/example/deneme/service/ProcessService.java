package com.example.deneme.service;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.controller.request.UpdateProcessRequest;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessService {
    List<ProcessDto> processList();
    void createProcess(CreateProcessRequest request) throws UserNotFoundException;
    ProcessDto getProcessById(int id) throws ProcessNotFoundException;
    ProcessDto updateProcess(int id, UpdateProcessRequest request) throws ProcessNotFoundException;
    ProcessDto deleteProcess(int id) throws ProcessNotFoundException;
    ProcessDto assignProcess(int userid, int processid) throws ProcessNotFoundException,UserNotFoundException;
    void assignStatus(CreateProcessRequest request, int processid) throws ProcessNotFoundException;
}
