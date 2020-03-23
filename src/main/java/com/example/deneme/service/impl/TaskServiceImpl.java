package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.CreateTaskRequestConverter;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.TaskService;
import org.omg.CORBA.TCKind;
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
    public List<TaskDto> taskList() {
        TaskEntity taskEntity = (TaskEntity) taskRepository.findAll();
        return (List<TaskDto>) TaskConverter.convert(taskEntity);
    }

    @Override
    public void createTask(CreateTaskRequest request) {
        TaskEntity taskEntity = CreateTaskRequestConverter.convert(request);
        taskRepository.save(taskEntity);}

    @Override
    public TaskDto getTaskById(int id) throws TaskNotFoundException{
          TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

          return TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(int id, TaskEntity taskEntityDetails)  throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setTaskName(taskEntityDetails.getTaskName());
        taskEntity.setEndDate(taskEntityDetails.getEndDate());
        taskEntity.setStartDate(taskEntityDetails.getStartDate());
        taskEntity.setUserEntity(taskEntityDetails.getUserEntity());
        taskEntity.setStatus("Done");

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);
    }

    @Override
    public TaskDto assignTask(int userid, int taskid) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskid)
                .orElseThrow(() -> new TaskNotFoundException(taskid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException(userid));

        taskEntity.setUserEntity(userEntity);

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);
    }

    @Override
    public void deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepository.delete(taskEntity);
    }


}
