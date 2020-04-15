package com.example.deneme.service.scheduled;

import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.MetricRepository;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MetricControlJob {
    @Autowired
    MetricRepository metricRepository;
    @Autowired
    UserRepository userRepository;

    public void Scheduled() throws UserNotFoundException {
        List<MetricEntity> metricEntityList = metricRepository.findAll();
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();

        List<MetricEntity> metricEntities = metricEntityList.stream()
                .filter(metricEntity -> metricEntity.getActual_end_date().getDayOfMonth() > metricEntity.getOriginal_end_date().getDayOfMonth())
                .collect(Collectors.toList());

        for(MetricEntity metricEntity : metricEntities){
            TaskDto taskDto = TaskConverter.convert(metricEntity.getTaskEntity());
            taskDtoList.add(taskDto);
            int x = taskDto.getUserId();
            UserEntity userEntity = userRepository.findById(x).orElseThrow(()->new UserNotFoundException(x));
            UserDto userDto = UserConverter.convert(userEntity);
            userDtoList.add(userDto);
        }

        System.out.println(metricEntities);
        System.out.println(taskDtoList);
        System.out.println(userDtoList);

    }



}
