package com.example.parkinglotsystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParkingSpots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Vehicle vehicle;

    @ManyToOne
    private Level level;

    @Enumerated(EnumType.STRING)
    private ParkingSpotStatus status;
}
