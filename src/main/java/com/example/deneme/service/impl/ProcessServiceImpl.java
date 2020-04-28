package com.example.deneme.service.impl;

import com.example.deneme.controller.request.CreateProcessRequest;
import com.example.deneme.controller.request.UpdateProcessRequest;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.model.converter.*;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final ProcessRepository processRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public ProcessServiceImpl(ProcessRepository processRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.processRepository = processRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ProcessDto> processList() {
        List<ProcessEntity> processEntities = processRepository.findAll();

        return ProcessConverter.convert(getNotDeletedProcess(processEntities));
    }

    private List<ProcessEntity> getNotDeletedProcess(List<ProcessEntity> processEntities) {
        return processEntities.stream()
                .filter(processEntity -> !processEntity.isDeleted())
                .collect(Collectors.toList());
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
    public ProcessDto getProcessById(Integer id) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        return processEntity.isDeleted() ? null : ProcessConverter.convert(processEntity);
    }

    @Override
    public ProcessDto updateProcess(Integer id, UpdateProcessRequest request) throws ProcessNotFoundException, UserNotFoundException {
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(()->new UserNotFoundException(request.getUserId()));

        prepareProcessEntity(request,processEntity,userEntity);

        return ProcessConverter.convert(processRepository.save(processEntity));
    }


    @Override
    public ProcessDto deleteProcess(Integer id) throws ProcessNotFoundException{
        ProcessEntity processEntity = processRepository.findById(id)
                .orElseThrow(() -> new ProcessNotFoundException(id));

        List<TaskEntity> taskList = processEntity.getTaskEntities();

        processEntity.setDeleted(true);

        taskList.stream().forEach(task -> task.setDeleted(true));

        return ProcessConverter.convert(processRepository.save(processEntity));
    }

    @Override
    public ProcessDto assignProcess(Integer userId, Integer processId) throws UserNotFoundException, ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processId)
                .orElseThrow(()-> new ProcessNotFoundException(processId));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));

        processEntity.setUserEntity(userEntity);

        return ProcessConverter.convert(processRepository.save(processEntity));
    }

    @Override
    public void assignStatus(CreateProcessRequest request , Integer processid) throws ProcessNotFoundException {
        ProcessEntity processEntity = processRepository.findById(processid)
                .orElseThrow(()-> new ProcessNotFoundException(processid));

        List<TaskEntity> taskEntities = taskRepository.findAllById(request.getTaskId());
        List<TaskDto> taskDtoList = TaskConverter.convert(taskEntities);

        setStatus(taskDtoList,processEntity);

    }

    private void setStatus(List<TaskDto> taskDtoList, ProcessEntity processEntity) {
        int countTask = 0;
        int countCompletedTask = 0;

        for(TaskDto taskDto: taskDtoList){
            if(taskDto.getStatus().equals(TaskStatus.DONE)){
                countCompletedTask++;
            }
            countTask++;
        }

        if(countTask == countCompletedTask){
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
