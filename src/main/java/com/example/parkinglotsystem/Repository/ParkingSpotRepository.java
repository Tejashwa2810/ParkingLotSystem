package com.example.parkinglotsystem.Repository;

import com.example.parkinglotsystem.Models.Level;
import com.example.parkinglotsystem.Models.ParkingSpotStatus;
import com.example.parkinglotsystem.Models.ParkingSpots;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpots, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM ParkingSpots p WHERE p.level.levelId = :levelId AND p.status = :status")
    List<ParkingSpots> findAvailableByLevel(
            @Param("levelId") int levelId,
            @Param("status") ParkingSpotStatus status,
            Pageable pageable
    );


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p from ParkingSpots p where p.vehicle.licensePlate = :vehicleLicense")
    ParkingSpots findOccupiedParking(@Param("vehicleLicense") String vehicleLicense);
}
