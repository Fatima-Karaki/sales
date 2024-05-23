package com.example.demo.repository;

import com.example.demo.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntityRepository extends JpaRepository<LogEntity, Long>
{
}