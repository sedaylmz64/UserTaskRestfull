package com.example.deneme.repositories;

import com.example.deneme.model.entity.MetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<MetricEntity, Integer> {
}
