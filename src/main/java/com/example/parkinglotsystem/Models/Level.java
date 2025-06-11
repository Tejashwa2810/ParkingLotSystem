package com.example.parkinglotsystem.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int levelId;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<ParkingSpots> parkingSpots;

    @ManyToOne
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkingLot;

    private int totalSpots;
}
