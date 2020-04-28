package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateUserRequest;
import com.example.deneme.model.dto.UserDto;
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

    @GetMapping("/users/taskMetric/{id}")
    public String getUserTaskMetric(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return userService.getUserTaskMetric(id);
    }

    @GetMapping("/users/{userName}")
    public List<UserDto> getUserListByName(@PathVariable(value = "userName")String userName){
        return userService.getUserListByName(userName);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable(value = "id") Integer id,
                                            @Validated UpdateUserRequest request) throws UserNotFoundException {
        return userService.updateUser(id,request);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
