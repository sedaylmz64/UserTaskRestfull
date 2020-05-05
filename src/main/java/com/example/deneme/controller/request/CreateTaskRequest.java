package com.example.deneme.controller.request;

import com.example.deneme.model.dto.MetricDto;
import java.time.LocalDate;
import java.util.List;

public class CreateTaskRequest {
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Boolean deleted;
    private String description;

    /*private String userName;
    private List<MetricDto> metricDtos;
    private String processName;*/

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

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<MetricDto> getMetricDtos() {
        return metricDtos;
    }

    public void setMetricDtos(List<MetricDto> metricDtos) {
        this.metricDtos = metricDtos;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }*/
}
