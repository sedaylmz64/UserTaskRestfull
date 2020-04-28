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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final MetricRepository metricRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, UserService userService, MetricRepository metricRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.metricRepository = metricRepository;
    }

    @Override
    public List<TaskDto> taskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();

        return TaskConverter.convert(getNotDeletedTask(taskEntities));
    }

    private List<TaskEntity> getNotDeletedTask(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .filter(taskEntity -> !taskEntity.isDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public void createTask(CreateTaskRequest request) throws UserNotFoundException {
        TaskEntity taskEntity = CreateTaskRequestConverter.convert(request);
        taskRepository.save(taskEntity);}

    @Override
    public TaskDto getTaskById(Integer id) throws TaskNotFoundException{
          TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

          return taskEntity.isDeleted() ? null : TaskConverter.convert(taskEntity);
    }

    @Override
    public TaskDto updateTask(Integer id, UpdateTaskRequest request) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        UserEntity userEntity = UserEntityConverter.convert(userService.getUserById(request.getUserId()));

        prepareTaskEntity(request,taskEntity,userEntity);

        return TaskConverter.convert(taskRepository.save(taskEntity));
    }


    @Override
    public TaskDto assignTask(Integer userid, Integer taskid) throws TaskNotFoundException, UserNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(taskid)
                .orElseThrow(() -> new TaskNotFoundException(taskid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException(userid));

        taskEntity.setUserEntity(userEntity);

        return TaskConverter.convert(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDto assignMetric(Integer taskid, CreateMetricRequest request) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));

        List<MetricDto> metricDtos = request.getMetrics();

        metricRepository.saveAll(MetricConverter.converts(metricDtos));

        taskEntity.setMetricEntities(MetricConverter.converts(metricDtos));

        setMetricTask(taskEntity);

        return TaskConverter.convert(taskRepository.save(taskEntity));
    }

    private void setMetricTask(TaskEntity taskEntity) {
        List<MetricEntity> metricEntity = taskEntity.getMetricEntities();

        metricEntity.stream().forEach(metricEntity1->metricEntity1.setTaskEntity(taskEntity));
    }

    @Override
    public TaskDto deleteTask(Integer id) throws TaskNotFoundException {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskEntity.setDeleted(true);

        return TaskConverter.convert(taskRepository.save(taskEntity));
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
