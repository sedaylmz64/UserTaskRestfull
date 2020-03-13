package com.example.deneme.service;

import com.example.deneme.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public List<UserEntity> userList();
    public UserEntity createUser(UserEntity userEntity);
    public UserEntity getUserById(int id) throws UserNotFoundException;
    public UserEntity updateUser(int id, UserEntity userEntityDetails) throws UserNotFoundException;
    public ResponseEntity<?> deleteUser(int id) throws UserNotFoundException;
}
