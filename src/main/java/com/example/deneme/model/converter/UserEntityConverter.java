package com.example.deneme.model.converter;

import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.UserEntity;

public class UserEntityConverter {
    public static UserEntity convert(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getUserId());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setRole(userDto.getRole());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setTaskEntityList(TaskConverter.converts(userDto.getTaskDtoList()));

        return userEntity;
    }
}
