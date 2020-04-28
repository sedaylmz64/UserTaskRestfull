package com.example.deneme.exception;

public class UserNotFoundException extends Exception{
    private Integer id;
    private String userName;
    public UserNotFoundException(Integer id) {
        super(String.format("UserEntity is not found with id : '%d'", id));
    }
    public UserNotFoundException(String userName) {
        super(String.format("UserEntity is not found with id : '%s'", userName));
    }

}
