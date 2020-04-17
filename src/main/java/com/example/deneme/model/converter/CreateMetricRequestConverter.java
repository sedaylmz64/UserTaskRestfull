package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateMetricRequest;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class CreateMetricRequestConverter {
    @Autowired
    private static TaskRepository taskRepository;
    @Autowired
    private static TaskDto taskDto;

    public static List<MetricEntity> convert(CreateMetricRequest request){
        return request.getMetrics()
                .stream().map(CreateMetricRequestConverter::convert)
                .collect(Collectors.toList());
    }

    private static MetricEntity convert(MetricDto metricDto) {
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setId(metricDto.getMetricId());
        metricEntity.setStartDate(metricDto.getStartDate());
        metricEntity.setActualEndDate(metricDto.getActualEndDate());
        metricEntity.setOriginalEndDate(metricDto.getOriginalEndDate());

        try {
            taskDto = TaskConverter
                    .convert(taskRepository.findById(metricDto.getTaskId())
                            .orElseThrow(()->new TaskNotFoundException(metricDto.getTaskId())));
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }

        metricEntity.setTaskEntity(TaskConverter.convert(taskDto));

        return metricEntity;
    }
}
