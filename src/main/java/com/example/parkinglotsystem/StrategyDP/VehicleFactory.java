package com.example.parkinglotsystem.StrategyDP;


import com.example.parkinglotsystem.Models.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {
    public Vehicle createVehicle(Vehicle vehicleDetails) {
        Vehicle vehicle = new Vehicle();
        VehicleInterface vehicleStrategy;

        switch (vehicleDetails.getVehicleType()) {
            case CAR:
                vehicleStrategy = new Car();
                break;
            case TRUCK:
                vehicleStrategy = new Truck();
                break;
            case MOTORCYCLE:
                vehicleStrategy = new MotorCycle();
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + vehicleDetails.getVehicleType());
        }

        vehicleStrategy.setVehicle(vehicle);
        return vehicle;
    }
}
