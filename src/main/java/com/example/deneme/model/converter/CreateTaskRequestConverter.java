package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateTaskRequestConverter {

    public static TaskEntity convert(CreateTaskRequest request){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(request.getTaskName());
        taskEntity.setStartDate(request.getStartDate());
        taskEntity.setEndDate(request.getEndDate());
        taskEntity.setStatus(request.getStatus());
        taskEntity.setDeleted(request.getDeleted());
        taskEntity.setDescription(request.getDescription());

        /*taskEntity.setMetricEntities(MetricConverter.converts(request.getMetricDtos()));

        UserDto userDto = UserConverter.convert(userRepository.findByUserName(request.getUserName())
        .orElseThrow(()->new UserNotFoundException(request.getUserName())));

        taskEntity.setUserEntity(UserEntityConverter.convert(userDto));*/

        return taskEntity;
    }
}
