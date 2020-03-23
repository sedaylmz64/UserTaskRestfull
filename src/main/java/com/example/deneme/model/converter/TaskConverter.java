package com.example.deneme.model.converter;

import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.TaskEntity;

public class TaskConverter {
    public static TaskDto convert(TaskEntity taskEntity){
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(taskEntity.getTaskName());
        taskDto.setStartDate(taskEntity.getStartDate());
        taskDto.setEndDate(taskEntity.getEndDate());
        taskDto.setStatus(taskEntity.getStatus());
        taskDto.setTaskId(taskEntity.getId());

        return taskDto;
    }
}
