package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.model.entity.UserEntity;

public class CreateUserRequestConverter {

    public static UserEntity convert(CreateUserRequest request){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(request.getPassword());
        userEntity.setRole(request.getRole());
        userEntity.setUserName(request.getUserName());

        /*List<Integer> taskIdList = request.getTaskIdList();
        List<TaskEntity> taskEntityList = taskRepository.findAllById(taskIdList);
        userEntity.setTaskEntityList(taskEntityList);*/


        return userEntity;
    }

}
