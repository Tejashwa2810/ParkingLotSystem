package com.example.parkinglotsystem.StrategyDP;

import com.example.parkinglotsystem.Models.Vehicle;
import com.example.parkinglotsystem.Models.VehicleType;

public class Truck implements VehicleInterface{
    private Vehicle vehicle;

    public void setVehicle(Vehicle vehicle1) {
        this.vehicle = vehicle1;
        vehicle.setVehicleType(VehicleType.TRUCK);
        vehicle.setLicensePlate(vehicle1.getLicensePlate());
    }
}
