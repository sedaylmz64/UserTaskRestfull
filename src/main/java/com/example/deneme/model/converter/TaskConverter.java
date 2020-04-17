package com.example.deneme.model.converter;

import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TaskConverter {

    @Autowired
    private static UserRepository userRepository;

    public static List<TaskDto> convert(List<TaskEntity> taskEntityList){
        return taskEntityList
                .stream()
                .map(TaskConverter::convert)
                .collect(Collectors.toList());
    }

    public static TaskDto convert(TaskEntity taskEntity){
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(taskEntity.getTaskName());
        taskDto.setStartDate(taskEntity.getStartDate());
        taskDto.setEndDate(taskEntity.getEndDate());
        taskDto.setStatus(taskEntity.getStatus());
        taskDto.setTaskId(taskEntity.getId());
        taskDto.setDeleted(taskEntity.isDeleted());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setTaskId(taskEntity.getId());
        taskDto.setMetricDtoList(MetricConverter.convert(taskEntity.getMetricEntities()));

        return taskDto;
    }

    public static List<TaskEntity> converts(List<TaskDto> taskDtos){
        return taskDtos
                .stream()
                .map(TaskConverter::convert)
                .collect(Collectors.toList());
    }

    public static TaskEntity convert(TaskDto taskDto){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskDto.getTaskId());
        taskEntity.setTaskName(taskDto.getTaskName());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setStatus(taskDto.getStatus());
        taskEntity.setStartDate(taskDto.getStartDate());
        taskEntity.setEndDate(taskDto.getEndDate());
        taskEntity.setDeleted(taskDto.getDeleted());
        taskEntity.setMetricEntities(MetricConverter.converts(taskDto.getMetricDtoList()));
        int id = taskDto.getUserId();
        UserDto userDto = null;
        try {
            userDto = UserConverter.convert(userRepository.findById(id)
            .orElseThrow(()-> new UserNotFoundException(id)));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        taskEntity.setUserEntity(UserEntityConverter.convert(userDto));

        return taskEntity;
    }
}
