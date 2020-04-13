package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.controller.request.UpdateProcessRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.*;
import com.example.deneme.model.dto.ProcessDto;
import com.example.deneme.model.dto.TaskDto;
import com.example.deneme.model.dto.UserDto;
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
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<ProcessDto> processList() {
        List<ProcessEntity> processEntities = processRepository.findAll();

        List<ProcessEntity> processEntityList = processEntities.stream()
                .filter(processEntity -> !processEntity.isDeleted())
                .collect(Collectors.toList());



        return ProcessConverter.convert(processEntityList);
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
    public ProcessDto updateProcess(int id, UpdateProcessRequest request) throws ProcessNotFoundException, UserNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        UserDto userDto = userService.getUserById(request.getUserId());

        UserEntity userEntity = UserEntityConverter.convert(userDto);

        prepareProcessEntity(request,processEntity,userEntity);

        ProcessEntity updatedProcessEntity = processRepository.save(processEntity);

        return ProcessConverter.convert(updatedProcessEntity);
    }


    @Override
    public ProcessDto deleteProcess(int id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        List<TaskDto> taskDtoList = TaskConverter.convert(processEntity.getTaskEntities());

        processEntity.setDeleted(true);

        taskDtoList.stream().forEach(taskDto -> taskDto.setDeleted(true));

        //for(TaskDto taskDto : taskDtoList){ taskDto.setDeleted(true);}

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

    private void prepareProcessEntity(UpdateProcessRequest request, ProcessEntity processEntity, UserEntity userEntity) {
        if(request.getProcessName() != null){
            processEntity.setProcessName(request.getProcessName());
        }

        if(request.getUserId() != 0){
            processEntity.setUserEntity(userEntity);
        }

        if(request.getProcessStatus() != null){
            processEntity.setStatus(String.valueOf(request.getProcessStatus()));
        }
    }
}
