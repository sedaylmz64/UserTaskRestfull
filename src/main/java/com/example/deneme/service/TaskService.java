package com.example.deneme.service;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.UpdateTaskRequest;
import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    List<TaskDto> taskList();
    void createTask(CreateTaskRequest request);
    TaskDto getTaskById(int id) throws TaskNotFoundException;
    TaskDto updateTask(int id, UpdateTaskRequest request) throws TaskNotFoundException, UserNotFoundException;
    TaskDto deleteTask(int id) throws TaskNotFoundException;
    TaskDto assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException;

}
