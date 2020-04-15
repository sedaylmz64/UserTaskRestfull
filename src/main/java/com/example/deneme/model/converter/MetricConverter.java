package com.example.deneme.model.converter;

import com.example.deneme.model.dto.MetricDto;
import com.example.deneme.model.entity.MetricEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MetricConverter {
    public static List<MetricDto> convert(List<MetricEntity> metricEntityList){
        return metricEntityList
                .stream()
                .map(MetricConverter::convert)
                .collect(Collectors.toList());
    }

    public static MetricDto convert(MetricEntity metricEntity){
        MetricDto metricDto = new MetricDto();
        metricDto.setMetricId(metricEntity.getId());
        metricDto.setStart_date(metricEntity.getStart_date());
        metricDto.setOriginal_end_date(metricEntity.getOriginal_end_date());
        metricDto.setActual_end_date(metricEntity.getActual_end_date());
        //metricDto.setTaskId(metricEntity.);

        return metricDto;
    }
}
