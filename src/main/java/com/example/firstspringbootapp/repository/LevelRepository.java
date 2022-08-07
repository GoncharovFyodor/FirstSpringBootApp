package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
