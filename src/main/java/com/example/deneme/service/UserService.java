package com.example.deneme.service;

import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<UserEntity> userList();
    UserEntity createUser(UserEntity userEntity);
    UserEntity getUserById(int id) throws UserNotFoundException;
    UserEntity updateUser(int id, UserEntity userEntityDetails) throws UserNotFoundException;
    ResponseEntity<?> deleteUser(int id) throws UserNotFoundException;
}
