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

        return metricEntity;
    }
}
