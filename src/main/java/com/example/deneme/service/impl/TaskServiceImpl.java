package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.UpdateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.CreateTaskRequestConverter;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.converter.UserEntityConverter;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TaskDto> taskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();

        for(TaskEntity list : taskEntities){
            if(list.isDeleted())
                taskEntities.remove(list);
        }

        return TaskConverter.convert(taskEntities);
    }

    @Override
    public void createTask(CreateTaskRequest request) {
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

        UserServiceImpl userService = new UserServiceImpl();
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
