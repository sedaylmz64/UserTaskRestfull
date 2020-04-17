package com.example.deneme.controller.request;

import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.entity.MetricEntity;

import java.util.List;

public class CreateMetricRequest {
    private int taskId;
    private List<MetricDto> metrics;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public List<MetricDto> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDto> metrics) {
        this.metrics = metrics;
    }
}
