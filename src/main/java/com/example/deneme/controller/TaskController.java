package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.UpdateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskDto> taskList(){
        return taskService.taskList();
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody CreateTaskRequest request) throws UserNotFoundException {
        taskService.createTask(request);
    }

    @GetMapping("/tasks/{id}")
    public TaskDto getTaskById(@PathVariable(value = "id") Integer id) throws TaskNotFoundException {
        return taskService.getTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskDto updateTask(@PathVariable(value = "id") Integer id,
                              @Valid @RequestBody UpdateTaskRequest request) throws TaskNotFoundException, UserNotFoundException {
        return taskService.updateTask(id, request);
    }

    @PutMapping("/tasks/{taskId}/user/{userId}")
    public TaskDto assignTask(@PathVariable(value = "userId") Integer userId,@PathVariable(value = "taskId") Integer taskId) throws TaskNotFoundException, UserNotFoundException {
        return taskService.assignTask(userId,taskId);
    }

    @PutMapping("/tasks/assignMetric/{taskId}")
    public TaskDto assignMetric(@PathVariable(value = "taskId") Integer taskId, @Valid @RequestBody UpdateTaskRequest request) throws TaskNotFoundException{
        return taskService.assignMetric(taskId,request);
    }

    @DeleteMapping("/tasks/{id}")
    public TaskDto deleteTask(@PathVariable(value = "id") Integer id) throws TaskNotFoundException {
        return taskService.deleteTask(id);
    }
}
