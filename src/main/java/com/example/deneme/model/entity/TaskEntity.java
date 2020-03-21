package com.example.deneme.model.entity;



import javax.persistence.*;
import java.util.Date;

@SuppressWarnings("JpaModelReferenceInspection")
@Entity
@Table(name = "task")
public class TaskEntity {

    public TaskEntity(){

    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task_name" )
    private String taskName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private ProcessEntity processEntity;

    public TaskEntity(String taskName, Date startDate, Date endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProcessEntity getProcess() {
        return processEntity;
    }

    public void setProcess(ProcessEntity processEntity) {
        this.processEntity = processEntity;
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

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
