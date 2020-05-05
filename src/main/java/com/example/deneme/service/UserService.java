package com.example.deneme.service;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateUserRequest;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserService {

    List<UserDto> userList();
    void createUser(CreateUserRequest request);
    UserDto getUserById(Integer id) throws UserNotFoundException;
    UserDto updateUser(Integer id, UpdateUserRequest request) throws UserNotFoundException;
    void deleteUser(Integer id) throws UserNotFoundException;
    Map<List<TaskDto>, List<MetricDto>> getUserTaskMetric(Integer id) throws UserNotFoundException;
    List<UserDto> getUserListByName(String userName);

}
