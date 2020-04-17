package com.example.deneme.exception;

public class UserNotFoundException extends Exception{
    private int id;
    private String userName;
    public UserNotFoundException(int id) {
        super(String.format("UserEntity is not found with id : '%d'", id));
    }
    public UserNotFoundException(String userName) {
        super(String.format("UserEntity is not found with id : '%s'", userName));
    }

}
