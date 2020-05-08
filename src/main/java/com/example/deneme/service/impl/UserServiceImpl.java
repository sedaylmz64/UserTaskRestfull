package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateUserRequest;
import com.example.deneme.model.converter.CreateUserRequestConverter;
import com.example.deneme.model.converter.MetricConverter;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<List<TaskDto>, List<MetricDto>> getUserTaskMetric(Integer id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<TaskEntity> taskList = userEntity.getTaskEntityList();

        List<MetricEntity> metricList = getMetricList(taskList);

        Map<List<TaskDto>, List<MetricDto>> taskMetricMap = new HashMap<>();
            taskMetricMap.put(TaskConverter.convert(taskList), MetricConverter.convert(metricList));

        System.out.println(taskList);
        System.out.println(metricList);

        return taskMetricMap;
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
