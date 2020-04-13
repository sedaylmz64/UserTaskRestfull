package com.example.deneme.service;

import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import org.springframework.stereotype.Component;

@Component
public interface MetricService {
    MetricDto assignTask(int userid, int taskid) throws TaskNotFoundException, MetricNotFoundException;
}
