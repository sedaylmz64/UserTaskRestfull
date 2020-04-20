package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateMetricRequest;
import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.UpdateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.*;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.MetricRepository;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.TaskService;
import com.example.deneme.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MetricRepository metricRepository;
    @Autowired
    private TaskService taskService;

    @Override
    public List<TaskDto> taskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();

        List<TaskEntity> taskEntityList = taskEntities.stream()
                .filter(taskEntity -> taskEntity.isDeleted())
                .collect(Collectors.toList());

        return TaskConverter.convert(taskEntityList);
    }

    @Override
    public void createTask(CreateTaskRequest request) throws UserNotFoundException {
        TaskEntity taskEntity = CreateTaskRequestConverter.convert(request);
        taskRepository.save(taskEntity);}

    @Override
    public TaskDto getTaskById(int id) throws TaskNotFoundException{
          TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

          if(taskEntity.isDeleted())
              return null;

          return TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(int id, UpdateTaskRequest request) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        UserDto userDto = userService.getUserById(request.getUserId());

        UserEntity userEntity = UserEntityConverter.convert(userDto);

        prepareTaskEntity(request,taskEntity,userEntity);

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
    public TaskDto assignMetric(int taskid, CreateMetricRequest request) throws TaskNotFoundException {
        MetricEntity metricEntity = new MetricEntity();
        TaskEntity taskEntity = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        List<MetricDto> metricDtos = request.getMetrics();

        metricRepository.saveAll(MetricConverter.converts(metricDtos));

        metricEntity.setTaskEntity(taskEntity);
        taskEntity.setMetricEntities(MetricConverter.converts(metricDtos));

        TaskEntity updatedTask = taskRepository.save(taskEntity);

        return TaskConverter.convert(updatedTask);
    }

    @Override
    public TaskDto deleteTask(int id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setDeleted(true);

        TaskEntity updatedTask = taskRepository.save(taskEntity);
        return TaskConverter.convert(updatedTask);
    }

    private void prepareTaskEntity(UpdateTaskRequest request, TaskEntity taskEntity, UserEntity userEntity) {
        if(request.getTaskName() != null){
            taskEntity.setTaskName(request.getTaskName());
        }

        if(request.getStatus() != null){
            taskEntity.setStatus(String.valueOf(request.getStatus()));
        }

        if(request.getDescription() != null){
            taskEntity.setDescription(request.getDescription());
        }

        if(request.getUserId() != 0){
            taskEntity.setUserEntity(userEntity);
        }
    }

}
