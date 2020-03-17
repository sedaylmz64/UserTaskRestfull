package com.example.deneme.service;

import com.example.deneme.entity.TaskEntity;
import com.example.deneme.entity.UserEntity;
import com.example.deneme.exception.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    public List<TaskEntity> taskList();
    public TaskEntity createTask(TaskEntity taskEntity);
    public TaskEntity getTaskById(int id) throws TaskNotFoundException;
    public TaskEntity updateTask(int id, TaskEntity taskEntityDetails) throws TaskNotFoundException;
    public ResponseEntity<?> deleteTask(int id) throws TaskNotFoundException;
}