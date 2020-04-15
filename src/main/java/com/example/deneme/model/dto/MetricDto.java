package com.example.deneme.model.dto;

import com.example.deneme.model.enums.MetricType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MetricDto implements Serializable {
    private int metricId;
    private MetricType metricType;
    private LocalDate start_date;
    private LocalDate original_end_date;
    private LocalDate actual_end_date;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getOriginal_end_date() {
        return original_end_date;
    }

    public void setOriginal_end_date(LocalDate original_end_date) {
        this.original_end_date = original_end_date;
    }

    public LocalDate getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(LocalDate actual_end_date) {
        this.actual_end_date = actual_end_date;
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
                ", start_date=" + start_date +
                ", original_end_date=" + original_end_date +
                ", actual_end_date=" + actual_end_date +
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
                Objects.equals(start_date, metricDto.start_date) &&
                Objects.equals(original_end_date, metricDto.original_end_date) &&
                Objects.equals(actual_end_date, metricDto.actual_end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, metricType, start_date, original_end_date, actual_end_date, taskId);
    }
}
