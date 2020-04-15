package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateMetricRequest;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateMetricRequestConverter {
    @Autowired
    private static TaskRepository taskRepository;

    public static MetricEntity convert(CreateMetricRequest request) throws TaskNotFoundException {
        MetricEntity metricEntity = new MetricEntity();
        /*TaskEntity taskEntity = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));
        metricEntity.setTaskEntity(taskEntity);*/


        /*metricEntity.setTaskId(request.getTaskId());
        metricEntity.setMetrics(request.getMetrics());
        */

        return metricEntity;
    }
}
