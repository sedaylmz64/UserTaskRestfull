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
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "metric_type")
    private MetricType metricType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "original_end_date")
    private LocalDate originalEndDate;

    @Column(name = "actual_end_date")
    private LocalDate actualEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private TaskEntity taskEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false)
    private UserEntity userEntity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public MetricEntity() {
    }

    @Override
    public String toString() {
        return "MetricEntity{" +
                "id=" + id +
                ", metricType=" + metricType +
                ", start_date=" + startDate +
                ", original_end_date=" + originalEndDate +
                ", actual_end_date=" + actualEndDate +
                ", taskEntity=" + taskEntity +
                '}';
    }
}
