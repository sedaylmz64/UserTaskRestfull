package com.example.deneme.model.converter;

import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProcessConverter {

    public static List<ProcessDto> convert(List<ProcessEntity> processEntities){
        return processEntities
                .stream()
                .map(ProcessConverter::convert)
                .collect(Collectors.toList());
    }

    public static ProcessDto convert(ProcessEntity processEntity){
        ProcessDto processDto = new ProcessDto();
        processDto.setProcessId(processEntity.getId());
        processDto.setProcessName(processEntity.getProcessName());
        processDto.setStartDate(processEntity.getStartDate());
        processDto.setEndDate(processEntity.getEndDate());
        processDto.setStatus(processEntity.getStatus());
        processDto.setDeleted(processEntity.isDeleted());
        processDto.setTaskDtoList(TaskConverter.convert(processEntity.getTaskEntities()));
        //processDto.setUserId(processEntity.getUserId());

       return processDto;
    }
}
