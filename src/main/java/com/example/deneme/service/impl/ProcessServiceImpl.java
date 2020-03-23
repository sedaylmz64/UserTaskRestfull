package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.model.converter.CreateProcessRequestConverter;
import com.example.deneme.model.converter.ProcessConverter;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import com.example.deneme.repositories.ProcessRepository;
import com.example.deneme.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("processServiceImpl")
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;

    @Override
    public List<ProcessDto> processList() {
        ProcessEntity processEntity = (ProcessEntity) processRepository.findAll();

        return (List<ProcessDto>) ProcessConverter.convert(processEntity);
    }

    @Override
    public void createProcess(CreateProcessRequest request) {
        ProcessEntity processEntity = CreateProcessRequestConverter.convert(request);
        processRepository.save(processEntity);
    }

    @Override
    public ProcessDto getProcessById(int id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processEntity.setProcessName(processEntityDetails.getProcessName());
        processEntity.setStartDate(processEntityDetails.getStartDate());
        processEntity.setEndDate(processEntityDetails.getEndDate());
        processEntity.setStatus(processEntityDetails.getStatus());
        //processEntity.setTask(processEntityDetails.getTask());

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);
    }

    @Override
    public void deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processRepository.delete(processEntity);
    }
}
