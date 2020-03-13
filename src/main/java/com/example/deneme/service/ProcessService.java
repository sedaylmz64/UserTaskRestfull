package com.example.deneme.service;

import com.example.deneme.entity.ProcessEntity;
import com.example.deneme.entity.TaskEntity;
import com.example.deneme.entity.UserEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProcessService {
    public List<ProcessEntity> processList();
    public ProcessEntity createProcess(ProcessEntity processEntity);
    public ProcessEntity getProcessById(int id) throws ProcessNotFoundException;
    public ProcessEntity updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException;
    public ResponseEntity<?> deleteProcess(int id) throws ProcessNotFoundException;
}
