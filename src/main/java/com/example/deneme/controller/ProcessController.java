package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
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
    public List<ProcessDto> processList(){
        return processService.processList();
    }

    @PostMapping("/processes")
    public void createProcess(@RequestBody CreateProcessRequest request) {
        processService.createProcess(request);
    }

    @GetMapping("/processes/{id}")
    public ProcessDto getProcessById(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.getProcessById(id);
    }

    @PutMapping("/processes/{id}")
    public ProcessDto updateProcess(@PathVariable(value = "id") int id,
                                         @Valid @RequestBody ProcessEntity processDetails) throws ProcessNotFoundException {
        return processService.updateProcess(id, processDetails);
    }


    @DeleteMapping("/processes/{id}")
    public void deleteProcess(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        processService.deleteProcess(id);
    }


}
