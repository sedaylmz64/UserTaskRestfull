package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.model.dto.UserDto;
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
    public List<UserDto> userList(){
        return userService.userList();
    }

    /*@PostMapping("/users")
    public UserDto createUser(@Valid @RequestBody UserDto userEntity) {
        return userService.createUser(userEntity);
    }*/

    @PostMapping("/users")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }


    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }


    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable(value = "id") int id,
                                            @Validated UserEntity userEntityDetails) throws UserNotFoundException {
        return userService.updateUser(id,userEntityDetails);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
    }


}
