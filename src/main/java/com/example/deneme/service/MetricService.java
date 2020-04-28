package com.example.deneme.service;

import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import org.springframework.stereotype.Component;

@Component
public interface MetricService {
    MetricDto assignTask(Integer userid, Integer taskid) throws TaskNotFoundException, MetricNotFoundException;
}
