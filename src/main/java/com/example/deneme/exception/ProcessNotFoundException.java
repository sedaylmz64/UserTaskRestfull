package com.example.deneme.exception;

public class ProcessNotFoundException extends Exception {
    private Integer id;
    public ProcessNotFoundException(Integer id) {
        super(String.format("ProcessEntity is not found with id : '%d'", id));
    }

}
