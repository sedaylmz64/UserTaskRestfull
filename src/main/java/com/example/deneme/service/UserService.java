package com.example.deneme.service;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<UserDto> userList();
    void createUser(CreateUserRequest request);
    UserDto getUserById(int id) throws UserNotFoundException;
    UserDto updateUser(int id, UserEntity userEntityDetails) throws UserNotFoundException;
    void deleteUser(int id) throws UserNotFoundException;
}
