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
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final int FIRST_THREE_DIGIT = 3;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> userList() {
        return UserConverter.convert(userRepository.findAll());
    }

    @Override
    public void createUser(CreateUserRequest request) {
        UserEntity userEntity = CreateUserRequestConverter.convert(request);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserById(Integer id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserConverter.convert(userEntity);
    }

    @Override
    public UserDto updateUser(Integer id, UpdateUserRequest request)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        prepareUserEntity(request,userEntity);

        return UserConverter.convert(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Integer id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }

    @Override
    public String getUserTaskMetric(Integer id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<TaskEntity> taskList = userEntity.getTaskEntityList();

        List<MetricEntity> metricList = getMetricList(taskList);

       return taskList + "\n" + metricList;
    }


    private List<MetricEntity> getMetricList(List<TaskEntity> taskEntityList) {
        List<MetricEntity> metricEntityList = new ArrayList<>();

        for (TaskEntity taskEntity:taskEntityList){
            metricEntityList = taskEntity.getMetricEntities();
        }

        return metricEntityList;
    }

    @Override
    public List<UserDto> getUserListByName(String userName){
         return userName.length() < FIRST_THREE_DIGIT ? Collections.emptyList() :
                    UserConverter.convert(userRepository.findAllByUserName(userName));
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
