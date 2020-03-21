package com.example.deneme.service.impl;

import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskEntity> taskList() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity) {return taskRepository.save(taskEntity);}

    @Override
    public TaskEntity getTaskById(int id) throws TaskNotFoundException{
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public TaskEntity updateTask(int id, TaskEntity taskEntityDetails)  throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setTaskName(taskEntityDetails.getTaskName());
        taskEntity.setEndDate(taskEntityDetails.getEndDate());
        taskEntity.setStartDate(taskEntityDetails.getStartDate());
        taskEntity.setUserEntity(taskEntityDetails.getUserEntity());
        taskEntity.setStatus("Done");

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return updatedTask;
    }

    @Override
    public TaskEntity assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskid)
                .orElseThrow(() -> new TaskNotFoundException(taskid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException(userid));

        taskEntity.setUserEntity(userEntity);

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return updatedTask;
    }

    @Override
    public ResponseEntity<?> deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(taskEntity);

        return ResponseEntity.ok().build();
    }


}
