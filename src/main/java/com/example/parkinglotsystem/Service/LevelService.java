package com.example.parkinglotsystem.Service;

import com.example.parkinglotsystem.Models.Level;
import com.example.parkinglotsystem.Repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService {
    private LevelRepository levelRepository;
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    public List<Level> createMultipleLevel(List<Level> level) {
        List<Level> levelList = new ArrayList<>();
        for(Level l : level){
            levelRepository.save(l);
            levelList.add(l);
        }

        return levelList;
    }

}
