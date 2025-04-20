package com.example.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.practice.dto.VehicleDto;
import com.example.practice.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
     Optional<Vehicle> findByPlateNumber(String plateNumber);

     @Query(value = "SELECT v FROM Vehicle v WHERE v.departed = false ORDER BY v.arrivalTime ASC")
     List<Vehicle> findQueuedVehiclesUsingTimestanps();

     // VEHICLE WITHE THE EARIST TIMESTAMP
     Vehicle findFirstByDepartedFalseOrderByArrivalTimeAsc();

     // getting vehicles in queue
     List<VehicleDto> findByDepartedFalse();

     // getting vehcles travelling
     // @Query("SELECT v FROM Vehicle v WHERE v.departed = true AND v.isFull = true")
     List<Vehicle> findByDepartedTrue();

}
