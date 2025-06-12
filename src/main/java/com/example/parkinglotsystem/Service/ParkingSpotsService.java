package com.example.parkinglotsystem.Service;

import com.example.parkinglotsystem.Models.ParkingSpots;
import com.example.parkinglotsystem.Repository.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotsService {
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotsService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingSpots createSpot(ParkingSpots parkingSpots) {
        return parkingSpotRepository.save(parkingSpots);
    }

    public List<ParkingSpots> createMultipeSpot(List<ParkingSpots> parkingSpots) {
        List<ParkingSpots> newSpots = new ArrayList<>();
        for(ParkingSpots p: parkingSpots) {
            parkingSpotRepository.save(p);
            newSpots.add(p);
        }

        return newSpots;
    }
}
