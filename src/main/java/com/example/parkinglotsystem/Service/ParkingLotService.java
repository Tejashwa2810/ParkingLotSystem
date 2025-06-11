package com.example.parkinglotsystem.Service;

import com.example.parkinglotsystem.Models.*;
import com.example.parkinglotsystem.Repository.ParkingLotRepository;
import com.example.parkinglotsystem.Repository.ParkingSpotRepository;
import com.example.parkinglotsystem.Repository.VehicleRepository;
import com.example.parkinglotsystem.StrategyDP.VehicleFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;
    private ParkingSpots parkingSpots;
    private ParkingLot singletonInstance;
    private final ParkingSpotRepository parkingSpotRepository;
    private VehicleRepository vehicleRepository;
    private VehicleFactory vehicleFactory;

    public ParkingLotService(VehicleFactory vehicleFactory, VehicleRepository vehicleRepository, ParkingSpotRepository parkingSpotRepository, ParkingLotRepository parkingLotRepository) {
        this.vehicleFactory = vehicleFactory;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    @PostConstruct
    public void init(){
        if(parkingLotRepository.count() == 0){
            ParkingLot parkingLot = new ParkingLot();
            singletonInstance = parkingLotRepository.save(parkingLot);
        }else {
            singletonInstance = parkingLotRepository.findAll().get(0);
        }
    }

    public synchronized ParkingLot getParkingLot() {
        return singletonInstance;
    }

    public ParkingLot getOrCreateSingleton() {
        if (singletonInstance == null) {
            init();
        }
        return singletonInstance;
    }

    public List<ParkingSpots> availableSpots(ParkingLot singletonInstance) {
        List<ParkingSpots> parkingSpotsList = new ArrayList<>();
        List<Level> levelList = singletonInstance.getLevels();
        for(Level level : levelList){
            for(ParkingSpots parkingSpots : level.getParkingSpots()){
                if(parkingSpots.getStatus() == ParkingSpotStatus.VACANT){
                    parkingSpotsList.add(parkingSpots);
                }
            }
        }
        return parkingSpotsList;
    }

    @Transactional
    public String parkVehicle(Vehicle vehicle) {
        List<ParkingSpots> availableParkingSpotsList = new ArrayList<>();
        ParkingLot lot = getOrCreateSingleton();

        for(Level level : lot.getLevels()){
            availableParkingSpotsList = parkingSpotRepository.findAvailableByLevel(level.getLevelId(), ParkingSpotStatus.VACANT, PageRequest.of(0,1));
            if(!availableParkingSpotsList.isEmpty()){
                break;
            }
        }

        if(availableParkingSpotsList.isEmpty()){
            return "No Parking spots available.";
        }

        ParkingSpots parkingSpots = availableParkingSpotsList.get(0);

        parkingSpots.setStatus(ParkingSpotStatus.OCCUPIED);
        parkingSpots.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpots);

        return "The car has been parked on Spot#" + parkingSpots.getId();
    }


    @Transactional
    public String exitVehicle(Vehicle vehicle) {
        ParkingSpots occupiedParking = parkingSpotRepository.findOccupiedParking(vehicle.getLicensePlate());
        occupiedParking.setStatus(ParkingSpotStatus.VACANT);
        occupiedParking.setVehicle(null);
        parkingSpotRepository.save(occupiedParking);

        return "The has exited from the parking";
    }
}
