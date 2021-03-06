package com.example.deneme.controller;

import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {
    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PutMapping("/metrics/{metricId}/task/{taskId}")
    public MetricDto assignTask(@PathVariable(value = "taskId") Integer taskId, @PathVariable(value = "metricId") Integer metricId) throws TaskNotFoundException, MetricNotFoundException {
        return metricService.assignTask(taskId,metricId);
    }
}
