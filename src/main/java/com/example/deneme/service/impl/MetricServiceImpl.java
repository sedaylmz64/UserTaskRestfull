package com.example.deneme.service.impl;

import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.converter.MetricConverter;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.repositories.MetricRepository;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.service.MetricService;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;
    private final TaskRepository taskRepository;

    public MetricServiceImpl(MetricRepository metricRepository, TaskRepository taskRepository) {
        this.metricRepository = metricRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public MetricDto assignTask(Integer taskid, Integer metricid) throws TaskNotFoundException, MetricNotFoundException {
        MetricEntity metricEntity = metricRepository.findById(metricid)
                .orElseThrow(()->new MetricNotFoundException(metricid));

        TaskEntity taskEntity = taskRepository.findById(taskid)
                .orElseThrow(() -> new TaskNotFoundException(taskid));

        metricEntity.setTaskEntity(taskEntity);

        return MetricConverter.convert(metricRepository.save(metricEntity));
    }
}
