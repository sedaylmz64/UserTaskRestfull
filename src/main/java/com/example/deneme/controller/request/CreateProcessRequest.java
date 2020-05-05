package com.example.deneme.controller.request;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CreateProcessRequest {
    private String processName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Boolean deleted;
    /*private Integer userId;
    private Integer taskId;
    private List<Integer> taskIdList;
    private String userNames;*/


    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
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

    /*public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Iterable<Integer> getTaskId() {
        return Collections.singleton(taskId);
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public List<Integer> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<Integer> taskIdList) {
        this.taskIdList = taskIdList;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }*/
}
