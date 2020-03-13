package com.example.deneme.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "process")
public class ProcessEntity {

    public ProcessEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "processEntity",
            orphanRemoval = true)
    private List<TaskEntity> taskEntities = new ArrayList<>();

    public ProcessEntity(String processName, Date startDate, Date endDate) {
        this.processName = processName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setProcessName(String processName){
        this.processName = processName;
    }

    public String getProcessName(){
        return processName;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }


    public Date getStartDate(){
        return startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public List<TaskEntity> getTask(){
        return taskEntities;
    }

    public void setTask(List<TaskEntity> taskEntities){
        this.taskEntities = taskEntities;
    }

}
