package com.example.deneme.serviceImpl;

import com.example.deneme.entity.UserEntity;
import com.example.deneme.exception.UserNotFoundException;
import com.example.deneme.repositories.UserRepository;
import com.example.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> userList() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserEntity updateUser(int id, UserEntity userEntityDetails)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userEntity.setUserName(userEntityDetails.getUserName());
        userEntity.setPassword(userEntityDetails.getPassword());
        userEntity.setRole(userEntityDetails.getRole());
        //userEntity.setTaskEntities(userEntityDetails.getTaskEntities());

        UserEntity updateduser = userRepository.save(userEntity);

        return updateduser;
    }

    @Override
    public ResponseEntity<?> deleteUser(int id)  throws UserNotFoundException{
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);

        return ResponseEntity.ok().build();
    }
}
