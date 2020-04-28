package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.controller.request.UpdateProcessRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.exception.ProcessNotFoundException;
import com.example.deneme.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createProcess(@RequestBody CreateProcessRequest request) throws UserNotFoundException {
        processService.createProcess(request);
    }

    @GetMapping("/processes/{id}")
    public ProcessDto getProcessById(@PathVariable(value = "id") Integer id) throws ProcessNotFoundException {
        return processService.getProcessById(id);
    }

    @PutMapping("/processes/{id}")
    public ProcessDto updateProcess(@PathVariable(value = "id") Integer id,
                                         @Valid @RequestBody UpdateProcessRequest request) throws ProcessNotFoundException, UserNotFoundException {
        return processService.updateProcess(id, request);
    }

    @DeleteMapping("/processes/{id}")
    public ProcessDto deleteProcess(@PathVariable(value = "id") Integer id) throws ProcessNotFoundException {
        return processService.deleteProcess(id);
    }

    @PutMapping("/processes/{userId}/{processId}")
    public ProcessDto assignProcess(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "processId")
            Integer processId) throws ProcessNotFoundException, UserNotFoundException {
        return processService.assignProcess(userId,processId);
    }

    @PutMapping("/processes/status/{processId}")
    public void assignStatus(CreateProcessRequest request , @PathVariable(value = "processId") Integer processId) throws ProcessNotFoundException {
        processService.assignStatus(request,processId);
    }

}
