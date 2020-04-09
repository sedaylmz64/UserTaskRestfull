package com.example.deneme.model.converter;

import com.example.deneme.controller.request.CreateMetricRequest;
import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.entity.MetricEntity;

public class CreateMetricRequestConverter {
    public static MetricEntity convert(CreateMetricRequest request){
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setMetricType(request.getMetricType());
        metricEntity.setStart_date(request.getStart_date());
        metricEntity.setOriginal_end_date(request.getOriginal_end_date());
        metricEntity.setActual_end_date(request.getActual_end_date());
        metricEntity.setTaskEntity(request.getTaskEntity());

        return metricEntity;
    }
}
