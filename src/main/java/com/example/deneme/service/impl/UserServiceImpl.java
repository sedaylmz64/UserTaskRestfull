package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.model.converter.CreateUserRequestConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /*@Override
    public List<UserEntity> userList() {
        return userRepository.findAll();
    }*/

    @Override
    public List<UserDto> userList() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserConverter.convert(userEntities);
    }


    @Override
    public void createUser(CreateUserRequest request) {
        UserEntity userEntity = CreateUserRequestConverter.convert(request);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserById(int id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserConverter.convert(userEntity);
    }

    @Override
    public UserDto updateUser(int id, UserEntity userEntityDetails)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userEntity.setUserName(userEntityDetails.getUserName());
        userEntity.setPassword(userEntityDetails.getPassword());
        userEntity.setRole(userEntityDetails.getRole());
        //userEntity.setTaskEntities(userEntityDetails.getTaskEntities());

        UserEntity updateduser = userRepository.save(userEntity);

        return UserConverter.convert(updateduser);
    }

    @Override
    public void deleteUser(int id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }
}
