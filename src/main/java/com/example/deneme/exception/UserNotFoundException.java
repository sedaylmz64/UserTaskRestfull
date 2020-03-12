package com.example.deneme.exception;

public class UserNotFoundException extends Exception{
    private int id;
    public UserNotFoundException(int id) {
        super(String.format("User is not found with id : '%s'", id));
    }
}
