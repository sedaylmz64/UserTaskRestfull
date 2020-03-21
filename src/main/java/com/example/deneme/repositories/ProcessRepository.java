package com.example.deneme.repositories;

import com.example.deneme.model.entity.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity,Integer> {
}
