package com.example.parkinglotsystem.Service;

import com.example.parkinglotsystem.Models.Vehicle;
import com.example.parkinglotsystem.Repository.VehicleRepository;
import com.example.parkinglotsystem.StrategyDP.VehicleFactory;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;
    private VehicleFactory vehicleFactory;

    public VehicleService(VehicleRepository vehicleRepository, VehicleFactory vehicleFactory) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleFactory = vehicleFactory;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicleFactory.createVehicle(vehicle));
    }
}
