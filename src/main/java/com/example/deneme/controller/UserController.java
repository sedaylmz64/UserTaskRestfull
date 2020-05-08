package com.example.deneme.controller;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateUserRequest;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> userList(){
        return userService.userList();
    }

    @GetMapping("/users/taskMetric/{id}")
    public Map<List<TaskDto>, List<MetricDto>> getUserTaskMetric(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return userService.getUserTaskMetric(id);
    }

    @GetMapping("/users/search/{userName}")
    public List<UserDto> getUserListByName(@PathVariable(value = "userName")String userName){
        return userService.getUserListByName(userName);
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable(value = "id") Integer id,
                                            @Validated @RequestBody UpdateUserRequest request) throws UserNotFoundException {
        return userService.updateUser(id,request);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}
