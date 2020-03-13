package com.example.deneme.controller;

import com.example.deneme.entity.Process;
import com.example.deneme.exception.ProcessNotFoundException;
import com.example.deneme.repositories.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProcessController {
    @Autowired
    private ProcessRepository processRepository;

    @GetMapping("/processes")
    public List<Process> processList(){
        return processRepository.findAll();
    }

    @PostMapping("/processes")
    public Process createTask(@Valid @RequestBody Process process) {
        return processRepository.save(process);
    }

    @GetMapping("/processes/{id}")
    public Process getTaskById(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return process;
    }

    @PutMapping("/processes/{id}")
    public Process updateProcesses(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Process taskDetails) throws ProcessNotFoundException {

        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        process.setProcessName(taskDetails.getProcessName());
        process.setStartDate(taskDetails.getStartDate());
        process.setEndDate(taskDetails.getEndDate());

        Process updatedProcess = processRepository.save(process);

        return updatedProcess;
    }


    @DeleteMapping("/processes/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        Process process = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        processRepository.delete(process);

        return ResponseEntity.ok().build();
    }


}
