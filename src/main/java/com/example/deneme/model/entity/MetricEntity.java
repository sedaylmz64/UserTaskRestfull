package com.example.deneme.model.entity;

import com.example.deneme.model.enums.MetricType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@SuppressWarnings("JpaModelReferenceInspection")
@Entity
@Table(name = "metric")
public class MetricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "metric_type")
    private MetricType metricType;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "original_end_date")
    private LocalDate original_end_date;

    @Column(name = "actual_end_date")
    private LocalDate actual_end_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private TaskEntity taskEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public MetricEntity() {
    }

    @Override
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", start_date=" + start_date +
                ", original_end_date=" + original_end_date +
                ", actual_end_date=" + actual_end_date +
                ", taskEntity=" + taskEntity +
                '}';
    }
}
