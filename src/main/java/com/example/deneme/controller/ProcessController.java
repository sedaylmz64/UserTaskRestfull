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
    public ProcessDto getProcessById(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.getProcessById(id);
    }

    @PutMapping("/processes/{id}")
    public ProcessDto updateProcess(@PathVariable(value = "id") int id,
                                         @Valid @RequestBody UpdateProcessRequest request) throws ProcessNotFoundException, UserNotFoundException {
        return processService.updateProcess(id, request);
    }

    @DeleteMapping("/processes/{id}")
    public ProcessDto deleteProcess(@PathVariable(value = "id") int id) throws ProcessNotFoundException {
        return processService.deleteProcess(id);
    }

    @PutMapping("/processes/{userid}/{processid}")
    public ProcessDto assignProcess(@PathVariable(value = "userid") int userid, @PathVariable(value = "processid")
            int processid) throws ProcessNotFoundException, UserNotFoundException {
        return processService.assignProcess(userid,processid);
    }

    @PutMapping("/processes/status/{processid}")
    public void assignStatus(CreateProcessRequest request , @PathVariable(value = "processid") int processid) throws ProcessNotFoundException {
        processService.assignStatus(request,processid);
    }

}
