package com.example.deneme.exception;

public class ProcessNotFoundException extends Exception {
    private int id;
    public ProcessNotFoundException(int id) {
        super(String.format("Process is not found with id : '%d'", id));
    }

}
