package com.example.deneme.controller;

import com.example.deneme.entity.Task;
import com.example.deneme.entity.Task;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> taskList(){
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task Task) {
        return taskRepository.save(Task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        Task x = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return x;
    }


    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable(value = "id") int id,
                           @Valid @RequestBody Task taskDetails) throws TaskNotFoundException {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setTaskName(taskDetails.getTaskName());
        task.setStartDate(taskDetails.getStartDate());
        task.setEndDate(taskDetails.getEndDate());
        task.setUser(taskDetails.getUser());

        Task updatedTask = taskRepository.save(task);

        return updatedTask;
    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(value = "id") int id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(task);

        return ResponseEntity.ok().build();
    }
    
}
