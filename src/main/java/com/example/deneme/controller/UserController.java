package com.example.deneme.controller;

import com.example.deneme.entity.User;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> userList(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") int id,
                           @Valid @RequestBody User userDetails) throws UserNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        user.setTasks(userDetails.getTasks());

        User updateduser = userRepository.save(user);

        return updateduser;
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
