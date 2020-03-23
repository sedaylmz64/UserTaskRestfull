package com.example.deneme.model.converter;

import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.UserEntity;

public class UserConverter {
    public static UserDto convert(UserEntity userEntity){
        UserDto userDto = new UserDto();
        //userDTO.setPassword(userEntity.getPassword());
        userDto.setRole(userEntity.getRole());
        userDto.setUserName(userEntity.getUserName());
        userDto.setUserId(userEntity.getId());

        return userDto;
    }

}
