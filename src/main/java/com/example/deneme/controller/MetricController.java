package com.example.deneme.controller;

import com.example.deneme.exception.MetricNotFoundException;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {
    @Autowired
    MetricService metricService;

    @PutMapping("/metrics/{taskid}/{metricid}")
    public MetricDto assignTask(@PathVariable(value = "taskid") int taskid, @PathVariable(value = "metricid") int metricid) throws TaskNotFoundException, MetricNotFoundException {
        return metricService.assignTask(taskid,metricid);
    }
}
