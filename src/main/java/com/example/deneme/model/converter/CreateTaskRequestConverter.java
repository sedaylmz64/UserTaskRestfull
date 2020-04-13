package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.TaskEntity;

public class CreateTaskRequestConverter {
    public static TaskEntity convert(CreateTaskRequest request){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName(request.getTaskName());
        taskEntity.setStartDate(request.getStartDate());
        taskEntity.setEndDate(request.getEndDate());
        taskEntity.setStatus(request.getStatus());
        taskEntity.setDeleted(request.getDeleted());
        taskEntity.setDescription(request.getDescription());
        taskEntity.setUserEntity(request.getUserEntity());
        taskEntity.setMetricEntities(request.getMetricEntities());

        return taskEntity;
    }
}
