package com.example.parkinglotsystem.StrategyDP;

import com.example.parkinglotsystem.Models.Vehicle;
import com.example.parkinglotsystem.Models.VehicleType;

public class MotorCycle implements VehicleInterface {
    private Vehicle vehicle;

    @Override
    public void setVehicle(Vehicle vehicle1){
        this.vehicle = vehicle1;
        vehicle.setVehicleType(VehicleType.MOTORCYCLE);
        vehicle.setLicensePlate(vehicle1.getLicensePlate());
    }
}
