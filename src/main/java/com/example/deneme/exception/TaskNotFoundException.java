package com.example.deneme.exception;

public class TaskNotFoundException extends Exception{
    private int id;
    public TaskNotFoundException(int id) {
        super(String.format("Task is not found with id : '%s'", id));
    }
}

