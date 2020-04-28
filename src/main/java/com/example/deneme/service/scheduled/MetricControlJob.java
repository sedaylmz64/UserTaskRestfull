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
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class MetricControlJob {
    @Autowired
    private MetricRepository metricRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    private final Supplier<Predicate<MetricDto>> findOverDeadline = () -> metricDto ->
            metricDto.getActualEndDate().getDayOfMonth()
            > metricDto.getOriginalEndDate().getDayOfMonth();

    @Scheduled(fixedRate = 5000)
    public void Scheduled() throws UserNotFoundException, TaskNotFoundException {
        List<TaskDto> overDeadLineTaskList = new ArrayList<>();
        List<UserDto> overDeadLineUserList = new ArrayList<>();
        List<MetricDto> allMetrics = MetricConverter.convert(metricRepository.findAll());

        List<MetricDto> overDeadlineMetricList = getOverDeadlineMetrics(allMetrics);

        addOverDeadlineUserTask(overDeadlineMetricList,overDeadLineTaskList,overDeadLineUserList);
        
        printOverDeadLineMetricTaskUserList(overDeadlineMetricList,overDeadLineTaskList,overDeadLineUserList);

    }

    private void addOverDeadlineUserTask(List<MetricDto> overDeadlineMetricList, List<TaskDto> overDeadLineTaskList, List<UserDto> overDeadLineUserList) throws TaskNotFoundException, UserNotFoundException {
        for(MetricDto overDeadlineMetric : overDeadlineMetricList){
            TaskDto overDeadLineTask = getOverDeadlineTask(overDeadlineMetric);

            overDeadLineTaskList.add(overDeadLineTask);

            overDeadLineUserList.add(getOverDeadlineUser(overDeadLineTask));
        }
    }
    
    private void printOverDeadLineMetricTaskUserList(List<MetricDto> overDeadlineMetricList, List<TaskDto> taskDtoList, List<UserDto> userDtoList) {
        printOverDeadLineMetricList(overDeadlineMetricList);
        printOverDeadLineTaskList(taskDtoList);
        printOverDeadLineUserList(userDtoList);
    }

    private TaskDto getOverDeadlineTask(MetricDto overDeadlineMetric) throws TaskNotFoundException {
        return TaskConverter.convert(taskRepository.findById(overDeadlineMetric.getTaskId())
                .orElseThrow(()->new TaskNotFoundException(overDeadlineMetric.getTaskId())));
    }

    private UserDto getOverDeadlineUser(TaskDto overDeadLineTask) throws UserNotFoundException {
         return UserConverter.convert(userRepository.findById(overDeadLineTask.getUserId())
                .orElseThrow(()->new UserNotFoundException(overDeadLineTask.getUserId())));
    }

    private void printOverDeadLineUserList(List<UserDto> userList) {
        userList.stream()
                .forEach(user-> System.out.println(user));
    }

    private void printOverDeadLineTaskList(List<TaskDto> taskList) {
        taskList.stream()
                .forEach(task-> System.out.println(task));
    }

    private void printOverDeadLineMetricList(List<MetricDto> overDeadlineMetricList) {
        overDeadlineMetricList.stream()
                .forEach(metric-> System.out.println(metric));
    }

    private List<MetricDto> getOverDeadlineMetrics(List<MetricDto> metricDtos) {
        return metricDtos
                .stream()
                .filter(findOverDeadline.get())
                .collect(Collectors.toList());
    }
}
