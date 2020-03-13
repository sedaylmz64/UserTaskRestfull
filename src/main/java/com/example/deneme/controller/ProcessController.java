package com.example.deneme.controller;

import com.example.deneme.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import com.example.deneme.repositories.ProcessRepository;
import com.example.deneme.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @GetMapping("/processes")
    public List<ProcessEntity> processList(){
        return processService.processList();
    }

    @PostMapping("/processes")
    public ProcessEntity createProcess(@Valid @RequestBody ProcessEntity processEntity) {
        return processService.createProcess(processEntity);
    }

    @GetMapping("/processes/{id}")
    public ProcessEntity getProcessById(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.getProcessById(id);
    }

    @PutMapping("/processes/{id}")
    public ProcessEntity updateProcess(@PathVariable(value = "id") int id,
                                         @Valid @RequestBody ProcessEntity processDetails) throws ProcessNotFoundException {
        return processService.updateProcess(id, processDetails);
    }


    @DeleteMapping("/processes/{id}")
    public ResponseEntity<?> deleteProcess(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.deleteProcess(id);
    }


}
