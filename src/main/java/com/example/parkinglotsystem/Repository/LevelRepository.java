package com.example.parkinglotsystem.Repository;

import com.example.parkinglotsystem.Models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
