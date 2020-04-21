package com.example.deneme.repositories;

import com.example.deneme.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String username);

    @Query("SELECT u.userName FROM UserEntity u WHERE u.userName LIKE :userName%")
    List<UserEntity> findAllByUserName(@Param("userName") String userName);
}
