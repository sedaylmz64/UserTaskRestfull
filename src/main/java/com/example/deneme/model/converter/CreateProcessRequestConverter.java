package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreateProcessRequestConverter {

    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private static TaskRepository taskRepository;

    public static ProcessEntity convert(CreateProcessRequest request) throws UserNotFoundException {
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setProcessName(request.getProcessName());
        processEntity.setStartDate(request.getStartDate());
        processEntity.setEndDate(request.getEndDate());
        processEntity.setStatus(request.getStatus());
        processEntity.setDeleted(request.getDeleted());
        List<Integer> taskIdList = request.getTaskIdList();
        String userName = request.getUserNames();

        List<TaskDto> taskDtoList = TaskConverter.convert(taskRepository.findAllById(taskIdList));
        UserDto userDto = UserConverter.convert(userRepository.findByName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName)));

        processEntity.setUserEntity(UserEntityConverter.convert(userDto));
        processEntity.setTaskEntities(TaskConverter.converts(taskDtoList));

        return processEntity;
    }
}
