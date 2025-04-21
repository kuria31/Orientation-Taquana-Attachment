package com.example.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.practice.dto.VehicleDto;
import com.example.practice.model.Vehicle;

/**
 * Repository interface for managing vehicle-related database operations.
 * Extends JpaRepository to provide CRUD operations for the Vehicle entity.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

     /**
      * Finds a vehicle by its plate number.
      *
      * @param plateNumber The plate number of the vehicle.
      * @return An Optional containing the vehicle if found, or empty if not found.
      */
     Optional<Vehicle> findByPlateNumber(String plateNumber);

     /**
      * Retrieves a list of vehicles currently in the queue, ordered by arrival time.
      *
      * @return A list of vehicles in the queue.
      */
     @Query(value = "SELECT v FROM Vehicle v WHERE v.departed = false ORDER BY v.arrivalTime ASC")
     List<Vehicle> findQueuedVehiclesUsingTimestanps();

     /**
      * Retrieves the first vehicle(at the Front of the queue) in the queue based on the earliest arrival time.
      *
      * @return The vehicle with the earliest arrival time that has not departed.
      */
     Vehicle findFirstByDepartedFalseOrderByArrivalTimeAsc();

     /**
      * Retrieves all vehicles currently in the queue.
      *
      * @return A list of vehicles in the queue.
      */
     List<VehicleDto> findByDepartedFalse();

     /**
      * Retrieves all vehicles that are currently traveling.
      *
      * @return A list of vehicles that are traveling.
      */
     List<Vehicle> findByDepartedTrue();
}
