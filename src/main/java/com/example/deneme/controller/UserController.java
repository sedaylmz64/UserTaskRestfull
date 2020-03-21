package com.example.deneme.controller;

import com.example.deneme.model.dto.RequestDTO;
import com.example.deneme.model.dto.ResponseDTO;
import com.example.deneme.model.dto.UserDTO;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserEntity> userList(){
        return userService.userList();
    }

    /*@PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody UserDTO userEntity) {
        return userService.createUser(userEntity);
    }*/

    @PostMapping("/users")
    public ResponseDTO<UserDTO> createUser(@RequestDTO(UserDTO.class) @Validated UserEntity userEntity) {
        return ResponseDTO.accepted().convertTo(userService.createUser(userEntity), UserDTO.class);
    }


    @GetMapping("/users/{id}")
    public ResponseDTO<UserDTO> getUserById(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return ResponseDTO.accepted().convertTo(userService.getUserById(id), UserDTO.class);
    }


    @PutMapping("/users/{id}")
    public ResponseDTO<UserDTO> updateUser(@PathVariable(value = "id") int id,
                                           @RequestDTO(UserDTO.class) @Validated UserEntity userEntityDetails) throws UserNotFoundException {
        return ResponseDTO.accepted().convertTo(userService.updateUser(id,userEntityDetails),UserDTO.class);
    }


    @DeleteMapping("/users/{id}")
    public ResponseDTO<UserDTO> deleteUser(@PathVariable(value = "id") int id) throws UserNotFoundException {

        return ResponseDTO.accepted().convertTo(userService.deleteUser(id),UserDTO.class);
    }


}
