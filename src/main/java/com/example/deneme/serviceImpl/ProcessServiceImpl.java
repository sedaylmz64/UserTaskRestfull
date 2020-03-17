package com.example.deneme.serviceImpl;

import com.example.deneme.entity.ProcessEntity;
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
    public List<ProcessEntity> processList() {
        return processRepository.findAll();
    }

    @Override
    public ProcessEntity createProcess(ProcessEntity processEntity) {
        return processRepository.save(processEntity);
    }

    @Override
    public ProcessEntity getProcessById(int id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return processEntity;
    }

    @Override
    public ProcessEntity updateProcess(int id, ProcessEntity processEntityDetails) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processEntity.setProcessName(processEntityDetails.getProcessName());
        processEntity.setStartDate(processEntityDetails.getStartDate());
        processEntity.setEndDate(processEntityDetails.getEndDate());
        processEntity.setStatus(processEntityDetails.getStatus());
        //processEntity.setTask(processEntityDetails.getTask());

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return updatedProcessEntity;
    }

    @Override
    public ResponseEntity<?> deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processRepository.delete(processEntity);

        return ResponseEntity.ok().build();
    }
}
