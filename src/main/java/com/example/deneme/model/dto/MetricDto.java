package com.example.deneme.model.dto;

import com.example.deneme.model.enums.MetricType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MetricDto implements Serializable {
    private int metricId;
    private MetricType metricType;
    private LocalDate startDate;
    private LocalDate originalEndDate;
    private LocalDate actualEndDate;
    private int taskId;

    public int getMetricId() {
        return metricId;
    }

    public void setMetricId(int metricId) {
        this.metricId = metricId;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getOriginalEndDate() {
        return originalEndDate;
    }

    public void setOriginalEndDate(LocalDate originalEndDate) {
        this.originalEndDate = originalEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "MetricDto{" +
                "metricId=" + metricId +
                ", metricType=" + metricType +
                ", start_date=" + startDate +
                ", original_end_date=" + originalEndDate +
                ", actual_end_date=" + actualEndDate +
                ", taskId=" + taskId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricDto metricDto = (MetricDto) o;
        return metricId == metricDto.metricId &&
                taskId == metricDto.taskId &&
                metricType == metricDto.metricType &&
                Objects.equals(startDate, metricDto.startDate) &&
                Objects.equals(originalEndDate, metricDto.originalEndDate) &&
                Objects.equals(actualEndDate, metricDto.actualEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, metricType, startDate, originalEndDate, actualEndDate, taskId);
    }
}
