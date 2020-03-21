package com.example.deneme.controller;

import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskEntity> taskList(){
        return taskService.taskList();
    }

    @PostMapping("/tasks")
    public TaskEntity createTask(@Valid @RequestBody TaskEntity taskEntity) {
        return taskService.createTask(taskEntity);
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getTaskById(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        return taskService.getTaskById(id);
    }


    @PutMapping("/tasks/{id}")
    public TaskEntity updateTask(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody TaskEntity taskEntityDetails) throws TaskNotFoundException {
        return taskService.updateTask(id, taskEntityDetails);

    }

    @PutMapping("/tasks/{userid}/{taskid}")
    public TaskEntity assignTask(@PathVariable(value = "userid") int userid,@PathVariable(value = "taskid") int taskid) throws TaskNotFoundException, UserNotFoundException {
        return taskService.assignTask(userid,taskid);

    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        return taskService.deleteTask(id);
    }
    
}
