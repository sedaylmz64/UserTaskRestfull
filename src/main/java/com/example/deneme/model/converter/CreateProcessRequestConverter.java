package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.model.entity.ProcessEntity;

public class CreateProcessRequestConverter {

    public static ProcessEntity convert(CreateProcessRequest request) {
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setProcessName(request.getProcessName());
        processEntity.setStartDate(request.getStartDate());
        processEntity.setEndDate(request.getEndDate());
        processEntity.setStatus(request.getStatus());
        processEntity.setDeleted(request.getDeleted());

        /*List<Integer> taskIdList = request.getTaskIdList();
        String userName = request.getUserNames();

        List<TaskDto> taskDtoList = TaskConverter.convert(taskRepository.findAllById(taskIdList));
        UserDto userDto = UserConverter.convert(userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName)));

        processEntity.setUserEntity(UserEntityConverter.convert(userDto));
        processEntity.setTaskEntities(TaskConverter.converts(taskDtoList));*/

        return processEntity;
    }
}
