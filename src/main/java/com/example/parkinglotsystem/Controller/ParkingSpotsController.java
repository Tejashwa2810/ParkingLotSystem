package com.example.parkinglotsystem.Controller;

import com.example.parkinglotsystem.Models.ParkingSpots;
import com.example.parkinglotsystem.Service.ParkingSpotsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingSpotsController {
    private ParkingSpotsService parkingSpotsService;

    public ParkingSpotsController(ParkingSpotsService parkingSpotsService) {
        this.parkingSpotsService = parkingSpotsService;
    }

    @PostMapping("/spot")
    public ParkingSpots createSpot(@RequestBody ParkingSpots parkingSpot) {
        return parkingSpotsService.createSpot(parkingSpot);
    }

    @PostMapping("/multiplespot")
    public List<ParkingSpots> createMultipeSpot(@RequestBody List<ParkingSpots> parkingSpot) {
        return parkingSpotsService.createMultipeSpot(parkingSpot);
    }
}
