package com.example.deneme.controller;

import com.example.deneme.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserEntity> userList(){
        return userService.userList();
    }

    @PostMapping("/users")
    public UserEntity createUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping("/users/{id}")
    public UserEntity getUserById(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }


    @PutMapping("/users/{id}")
    public UserEntity updateUser(@PathVariable(value = "id") int id,
                                 @Valid @RequestBody UserEntity userEntityDetails) throws UserNotFoundException {
        return userService.updateUser(id,userEntityDetails);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int id) throws UserNotFoundException {

        return userService.deleteUser(id);
    }
}
