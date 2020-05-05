package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateMetricRequest;
import com.example.deneme.model.entity.MetricEntity;



public class CreateMetricRequestConverter {

    private static MetricEntity convert(CreateMetricRequest request) {
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setStartDate(request.getStartDate());
        metricEntity.setActualEndDate(request.getActualEndDate());
        metricEntity.setOriginalEndDate(request.getOriginalEndDate());
        metricEntity.setMetricType(request.getMetricType());

        /*try {
            taskDto = TaskConverter
                    .convert(taskRepository.findById(metricDto.getTaskId())
                            .orElseThrow(()->new TaskNotFoundException(metricDto.getTaskId())));
            userDto = UserConverter.convert(userRepository.findById(metricDto.getUserId())
                            .orElseThrow(()-> new UserNotFoundException(metricDto.getUserId())));
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        metricEntity.setTaskEntity(TaskConverter.convert(taskDto));
        metricEntity.setUserEntity(UserEntityConverter.convert(userDto));*/

        return metricEntity;
    }
}
