package com.example.deneme.controller.request;

import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.model.enums.ProcessStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateProcessRequest {
    private String processName;
    private ProcessStatus processStatus;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
