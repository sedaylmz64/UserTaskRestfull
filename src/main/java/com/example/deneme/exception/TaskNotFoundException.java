package com.example.deneme.exception;

public class TaskNotFoundException extends Exception{
    private Integer id;
    public TaskNotFoundException(Integer id) {
        super(String.format("TaskEntity is not found with id : '%d'", id));
    }
}

