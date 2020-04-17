package com.example.deneme.model.converter;

import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.repositories.MetricRepository;
import com.example.deneme.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class MetricConverter {

    @Autowired
    private static TaskRepository taskRepository;

    public static List<MetricDto> convert(List<MetricEntity> metricEntityList){
        return metricEntityList
                .stream()
                .map(MetricConverter::convert)
                .collect(Collectors.toList());
    }

    public static MetricDto convert(MetricEntity metricEntity){
        MetricDto metricDto = new MetricDto();
        metricDto.setMetricId(metricEntity.getId());
        metricDto.setStartDate(metricEntity.getStartDate());
        metricDto.setOriginalEndDate(metricEntity.getOriginalEndDate());
        metricDto.setActualEndDate(metricEntity.getActualEndDate());
        metricDto.setTaskId(metricEntity.getTaskEntity().getId());

        return metricDto;
    }

    public static List<MetricEntity> converts(List<MetricDto> metricDtos){
        return metricDtos
                .stream()
                .map(MetricConverter::convert)
                .collect(Collectors.toList());
    }

    public static MetricEntity convert(MetricDto metricDto){
        MetricEntity metricEntity = new MetricEntity();
        TaskDto taskDto = new TaskDto();
        metricEntity.setOriginalEndDate(metricDto.getOriginalEndDate());
        metricEntity.setActualEndDate(metricDto.getActualEndDate());
        metricEntity.setStartDate(metricDto.getStartDate());
        metricEntity.setId(metricDto.getMetricId());

        int id = metricDto.getTaskId();
        try {
            taskDto = TaskConverter.convert(taskRepository.findById(id)
            .orElseThrow(()-> new TaskNotFoundException(id)));
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }

        metricEntity.setTaskEntity(TaskConverter.convert(taskDto));

        return metricEntity;
    }
}
