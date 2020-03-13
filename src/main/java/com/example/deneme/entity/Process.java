package com.example.deneme.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "process")
public class Process{

    public Process(){

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
            mappedBy = "process",
            orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Process(String processName, Date startDate, Date endDate) {
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

    public List<Task> getTask(){
        return tasks;
    }

    public void setTask(List<Task> tasks){
        this.tasks = tasks;
    }

}
