package com.example.deneme.model.converter;

import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.entity.ProcessEntity;

public class ProcessConverter {
    public static ProcessDto convert(ProcessEntity processEntity){
        ProcessDto processDto = new ProcessDto();
        processDto.setProcessId(processEntity.getId());
        processDto.setProcessName(processEntity.getProcessName());
        processDto.setStartDate(processEntity.getStartDate());
        processDto.setEndDate(processEntity.getEndDate());
        processDto.setStatus(processEntity.getStatus());

       return processDto;
    }
}
