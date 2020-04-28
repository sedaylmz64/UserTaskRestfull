package com.example.deneme.controller.request;

import com.example.deneme.model.enums.ProcessStatus;

public class UpdateProcessRequest {
    private String processName;
    private ProcessStatus processStatus;
    private Integer userId;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
