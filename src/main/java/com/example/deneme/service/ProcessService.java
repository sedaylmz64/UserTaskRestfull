package com.example.deneme.service;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessService {
    List<ProcessDto> processList();
    void createProcess(CreateProcessRequest request);
    ProcessDto getProcessById(int id) throws ProcessNotFoundException;
    ProcessDto updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException;
    void deleteProcess(int id) throws ProcessNotFoundException;
}
