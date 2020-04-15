package com.example.deneme.controller.request;

import com.example.deneme.model.entity.MetricEntity;

import java.util.List;

public class CreateMetricRequest {
    private int taskId;
    private List<MetricEntity> metrics;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public List<MetricEntity> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricEntity> metrics) {
        this.metrics = metrics;
    }
}
