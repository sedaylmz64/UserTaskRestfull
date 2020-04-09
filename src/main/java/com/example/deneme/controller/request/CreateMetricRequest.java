package com.example.deneme.controller.request;

import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.enums.MetricType;

import java.util.Date;

public class CreateMetricRequest {
    private MetricType metricType;
    private Date start_date;
    private Date original_end_date;
    private Date actual_end_date;
    private TaskEntity taskEntity;

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getOriginal_end_date() {
        return original_end_date;
    }

    public void setOriginal_end_date(Date original_end_date) {
        this.original_end_date = original_end_date;
    }

    public Date getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(Date actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
