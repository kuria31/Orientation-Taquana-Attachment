package com.example.practice.service;

import java.util.List;

import com.example.practice.dto.VehicleDto;
import com.example.practice.model.Vehicle;

/**
 * Service interface for managing vehicle-related operations.
 */
public interface VehicleService {

     /**
      * Adds a new vehicle to the system.
      *(Adds a vehicle to the queue)
      * @param vehicleDto The vehicle data transfer object containing vehicle
      *                   details.
      * @return The saved vehicle entity.
      */
     Vehicle addVehicle(VehicleDto vehicleDto);

     /**
      * Retrieves all vehicles in the system.
      *
      * @return A list of all vehicles.
      */
     List<Vehicle> getAllVehicles();

     /**
      * Adds a vehicle to the queue upon arrival.(The vehicle is added to the front of the queue)
      *
      * @param vehicleDto The vehicle data transfer object containing vehicle
      *                   details.
      * @return The updated vehicle entity.
      */
     Vehicle addVehicleToQueueOnArrival(VehicleDto vehicleDto);

     /**
      * Retrieves the top vehicle in the queue based on arrival time.
      *(  The vehicle in the front of the queue.)
      * @return The top vehicle in the queue.
      */
     Vehicle getTopVehicle();

     /**
      * Retrieves all vehicles currently in the queue.(Vehicles that are in line)
      *
      * @return A list of vehicles in the queue.
      */
     List<VehicleDto> getVehiclesInQueue();

     /**
      * Retrieves all vehicles currently travelling.(Dequeued)
      *
      * @return A list of vehicles that are travelling.
      */
     List<Vehicle> getVehiclesTravelling();
}