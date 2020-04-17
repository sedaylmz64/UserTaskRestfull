package com.example.deneme.service.scheduled;

import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.MetricConverter;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.converter.UserConverter;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
import com.example.deneme.model.entity.MetricEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.repositories.MetricRepository;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetricControlJob {
    @Autowired
    private MetricRepository metricRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 5000)
    public void Scheduled() throws UserNotFoundException {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<UserDto> userDtoList = new ArrayList<>();
        List<MetricDto> metricDtos = MetricConverter.convert(metricRepository.findAll());
        List<MetricDto> metricDtoList = metricDtos.stream()
                .filter(metricDto -> metricDto.getActualEndDate().getDayOfMonth() > metricDto.getOriginalEndDate().getDayOfMonth())
                .collect(Collectors.toList());

        for(MetricDto metricDto : metricDtoList){
            TaskDto taskDto = null;
            try {
                taskDto = TaskConverter.convert(taskRepository.findById(metricDto.getTaskId())
                        .orElseThrow(()->new TaskNotFoundException(metricDto.getTaskId())));
            } catch (TaskNotFoundException e) {
                e.printStackTrace();
            }
            taskDtoList.add(taskDto);

            TaskDto finalTaskDto = taskDto;
            UserDto userDto = UserConverter.convert(userRepository.findById(taskDto.getUserId())
                    .orElseThrow(()->new UserNotFoundException(finalTaskDto.getUserId())));
            userDtoList.add(userDto);
        }

        for(int i = 0; i < metricDtoList.size(); i++){
            System.out.println(metricDtoList.get(i));
        }

        for(int i = 0; i < taskDtoList.size(); i++){
            System.out.println(taskDtoList.get(i));
        }

        for(int i = 0; i < userDtoList.size(); i++){
            System.out.println(userDtoList.get(i));
        }

    }
}
