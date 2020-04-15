package com.example.deneme.controller.request;

import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CreateTaskRequest {
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Boolean deleted;
    private String description;
    private UserEntity userEntity;
    private List<MetricEntity> metricEntities;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<MetricEntity> getMetricEntities() {
        return metricEntities;
    }

    public void setMetricEntities(List<MetricEntity> metricEntities) {
        this.metricEntities = metricEntities;
    }
}
