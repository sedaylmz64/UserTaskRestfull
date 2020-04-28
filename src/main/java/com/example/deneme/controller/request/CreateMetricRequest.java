package com.example.deneme.controller.request;

import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.entity.MetricEntity;

import java.util.List;

public class CreateMetricRequest {
    private Integer taskId;
    private Integer userId;
    private List<MetricDto> metrics;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<MetricDto> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDto> metrics) {
        this.metrics = metrics;
    }
}
