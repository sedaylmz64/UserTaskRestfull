package com.example.deneme.model.dto;

import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.enums.MetricType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class MetricDto implements Serializable {
    private int metricId;
    private MetricType metricType;
    private Date start_date;
    private Date original_end_date;
    private Date actual_end_date;
    private TaskEntity taskEntity;

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

    @Override
    public String toString() {
        return "MetricDto{" +
                "metricId=" + metricId +
                ", metricType=" + metricType +
                ", start_date=" + start_date +
                ", original_end_date=" + original_end_date +
                ", actual_end_date=" + actual_end_date +
                ", taskEntity=" + taskEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricDto metricDto = (MetricDto) o;
        return metricId == metricDto.metricId &&
                metricType == metricDto.metricType &&
                Objects.equals(start_date, metricDto.start_date) &&
                Objects.equals(original_end_date, metricDto.original_end_date) &&
                Objects.equals(actual_end_date, metricDto.actual_end_date) &&
                Objects.equals(taskEntity, metricDto.taskEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, metricType, start_date, original_end_date, actual_end_date, taskEntity);
    }
}