package com.example.deneme.model.dto;

import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.UserEntity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskDto implements Serializable {
    private int taskId;
    private String taskName;
    private Date startDate;
    private Date endDate;
    private String status;
    private Boolean deleted;
    private String description;
    private UserEntity userEntity;
    private List<MetricEntity> metricEntities;


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return taskId == taskDto.taskId &&
                Objects.equals(taskName, taskDto.taskName) &&
                Objects.equals(startDate, taskDto.startDate) &&
                Objects.equals(endDate, taskDto.endDate) &&
                Objects.equals(status, taskDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, startDate, endDate, status);
    }
}
