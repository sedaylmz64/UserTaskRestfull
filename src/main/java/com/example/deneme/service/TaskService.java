package com.example.deneme.service;

import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskService {
    List<TaskEntity> taskList();
    TaskEntity createTask(TaskEntity taskEntity);
    TaskEntity getTaskById(int id) throws TaskNotFoundException;
    TaskEntity updateTask(int id, TaskEntity taskEntityDetails) throws TaskNotFoundException;
    ResponseEntity<?> deleteTask(int id) throws TaskNotFoundException;
    TaskEntity assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException;

}
