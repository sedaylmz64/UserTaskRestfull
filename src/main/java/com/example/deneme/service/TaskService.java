package com.example.deneme.service;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.UpdateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.exception.TaskNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    List<TaskDto> taskList();
    void createTask(CreateTaskRequest request) throws UserNotFoundException;
    TaskDto getTaskById(Integer id) throws TaskNotFoundException;
    TaskDto updateTask(Integer id, UpdateTaskRequest request) throws TaskNotFoundException, UserNotFoundException;
    TaskDto deleteTask(Integer id) throws TaskNotFoundException;
    TaskDto assignTask(Integer userid, Integer taskid) throws TaskNotFoundException, UserNotFoundException;
    TaskDto assignMetric(Integer taskid, UpdateTaskRequest request) throws TaskNotFoundException;
}
