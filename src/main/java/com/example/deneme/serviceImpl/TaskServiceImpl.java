package com.example.deneme.serviceImpl;

import com.example.deneme.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.repositories.ProcessRepository;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


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

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return updatedTask;
    }

    @Override
    public ResponseEntity<?> deleteTask(int id) {
        return null;
    }
}
