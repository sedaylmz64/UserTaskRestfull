package com.example.deneme.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskDto implements Serializable {
    private Integer taskId;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Boolean deleted;
    private String description;
    private Integer userId;
    private List<MetricDto> metricDtoList;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<MetricDto> getMetricDtoList() {
        return metricDtoList;
    }

    public void setMetricDtoList(List<MetricDto> metricDtoList) {
        this.metricDtoList = metricDtoList;
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
