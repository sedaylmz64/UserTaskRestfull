package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateUserRequest;
import com.example.deneme.model.converter.CreateUserRequestConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;


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
    public UserDto updateUser(int id, UpdateUserRequest request)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        prepareUserEntity(request,userEntity);

        UserEntity updateduser = userRepository.save(userEntity);

        return UserConverter.convert(updateduser);
    }

    @Override
    public void deleteUser(int id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }

    @Override
    public String getUserTaskMetric(int id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<TaskEntity> taskEntityList = userEntity.getTaskEntityList();

        List<MetricEntity> metricEntityList = new ArrayList<>();

        for (TaskEntity taskEntity:taskEntityList){
            metricEntityList = taskEntity.getMetricEntities();
        }

        taskEntityList.stream().forEach(taskEntity -> taskEntity.toString());
        metricEntityList.stream().forEach(metricEntity -> metricEntity.toString());

       return taskEntityList + "\n" + metricEntityList;
    }

    @Override
    public List<UserDto> getUserListByName(String userName){
        if(userName.length() < 3)
            return Collections.emptyList();
        else {
            List<UserEntity> userEntityList = userRepository.findAllByUserName(userName);
            return UserConverter.convert(userEntityList);
        }
    }

    private void prepareUserEntity(UpdateUserRequest request, UserEntity userEntity) {
        if(request.getUserName() != null){
            userEntity.setUserName(request.getUserName());
        }

        if(request.getRole() != null){
            userEntity.setRole(request.getRole());
        }

        if(request.getPassword() != null){
            userEntity.setPassword(request.getPassword());
        }
    }
}
