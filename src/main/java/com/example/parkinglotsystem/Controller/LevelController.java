package com.example.parkinglotsystem.Controller;

import com.example.parkinglotsystem.Models.Level;
import com.example.parkinglotsystem.Service.LevelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class LevelController {
    private LevelService levelService;
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping("/level")
    public Level createLevel(@RequestBody Level level) {
        return levelService.createLevel(level);
    }

    @PostMapping("/multiplelevel")
    public List<Level> createMultipleLevel(@RequestBody List<Level> level) {
        return levelService.createMultipleLevel(level);
    }
}
