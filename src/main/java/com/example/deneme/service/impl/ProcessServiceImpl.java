package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.controller.request.CreateTaskRequest;
import com.example.deneme.controller.request.CreateUserRequest;
import com.example.deneme.controller.request.UpdateProcessRequest;
import com.example.deneme.exception.TaskNotFoundException;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.CreateProcessRequestConverter;
import com.example.deneme.model.converter.CreateUserRequestConverter;
import com.example.deneme.model.converter.ProcessConverter;
import com.example.deneme.model.converter.TaskConverter;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.exception.ProcessNotFoundException;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import com.example.deneme.model.enums.ProcessStatus;
import com.example.deneme.model.enums.TaskStatus;
import com.example.deneme.repositories.ProcessRepository;
import com.example.deneme.repositories.TaskRepository;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.valueOf;

@Service("processServiceImpl")
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<ProcessDto> processList() {
        List<ProcessEntity> processEntities = processRepository.findAll();

        for(ProcessEntity list : processEntities){
            if(list.isDeleted())
                return null;
        }

        return ProcessConverter.convert(processEntities);
    }

    @Override
    public void createProcess(CreateProcessRequest request) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(()->new UserNotFoundException(request.getUserId()));
        ProcessEntity processEntity = CreateProcessRequestConverter.convert(request);
        processEntity.setUserEntity(userEntity);
        processRepository.save(processEntity);
    }

    @Override
    public ProcessDto getProcessById(int id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        if(processEntity.isDeleted())
            return null;

        return ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(int id, UpdateProcessRequest request) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));


        if(request.getProcessName() != null){
            processEntity.setProcessName(request.getProcessName());
        }

        if(request.getStartDate() != null){
            processEntity.setStartDate(request.getStartDate());
        }

        if(request.getEndDate() != null){
            processEntity.setEndDate(request.getEndDate());
        }

        if(request.getProcessStatus() != null){
            processEntity.setStatus(String.valueOf(request.getProcessStatus()));
        }

        if(request.getDeleted() != null){
            processEntity.setDeleted(request.getDeleted());
        }

        if(request.getUserEntity() != null){
            processEntity.setUserEntity(request.getUserEntity());
        }

        if(request.getTaskEntities() != null){
            processEntity.setTaskEntities(request.getTaskEntities());
        }


        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);
    }

    @Override
    public ProcessDto deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        List<TaskDto> taskDtoList = TaskConverter.convert(processEntity.getTaskEntities());

        processEntity.setDeleted(true);

        for(TaskDto taskDto : taskDtoList){
            taskDto.setDeleted(true);
        }

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);
        return ProcessConverter.convert(updatedProcessEntity);
    }

    @Override
    public ProcessDto assignProcess(int userid, int processid) throws UserNotFoundException, ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processid)
                .orElseThrow(()-> new ProcessNotFoundException(processid));

        UserEntity userEntity = userRepository.findById(userid)
                .orElseThrow(()-> new UserNotFoundException(userid));

        processEntity.setUserEntity(userEntity);

        ProcessEntity uodatedProcess = processRepository.save(processEntity);

        return ProcessConverter.convert(uodatedProcess);
    }

    @Override
    public void assignStatus(CreateProcessRequest request , int processid) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processid)
                .orElseThrow(()-> new ProcessNotFoundException(processid));

        List<TaskEntity> taskEntities = taskRepository.findAllById(request.getTaskId());
        List<TaskDto> taskDtoList = TaskConverter.convert(taskEntities);

        int count1 = 0;
        int count2 = 0;

        for(TaskDto taskDto: taskDtoList){
            if(taskDto.getStatus().equals(TaskStatus.DONE)){
                count2++;
            }
            count1++;
        }

        if(count1 == count2){
            processEntity.setStatus(valueOf(ProcessStatus.DONE));
        }
    }
}
