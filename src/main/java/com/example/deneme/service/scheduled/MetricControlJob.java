package com.example.deneme.service.scheduled;

import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.repositories.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MetricControlJob {
    @Autowired
    MetricRepository metricRepository;

    public void Scheduled(){
        List<MetricEntity> metricEntityList = metricRepository.findAll();
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();

        List<MetricEntity> metricEntities = metricEntityList.stream()
                .filter(metricEntity -> metricEntity.getActual_end_date().getTime() > metricEntity.getOriginal_end_date().getTime())
                .collect(Collectors.toList());

        for(MetricEntity metricEntity : metricEntities){
            TaskDto taskDto = TaskConverter.convert(metricEntity.getTaskEntity());
            taskDtoList.add(taskDto);
            UserDto userDto = UserConverter.convert(taskDto.getUserEntity());
            userDtoList.add(userDto);
        }

        System.out.println(metricEntities);
        System.out.println(taskDtoList);
        System.out.println(userDtoList);

    }



}
