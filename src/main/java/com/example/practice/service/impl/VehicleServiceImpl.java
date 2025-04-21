package com.example.practice.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.practice.dto.VehicleDto;
import com.example.practice.exception.NoVehiclesException;
import com.example.practice.exception.NoVehiclesTravellingException;
import com.example.practice.exception.VehicleExistsException;
import com.example.practice.exception.VehicleStillInQueueException;
import com.example.practice.model.Vehicle;
import com.example.practice.repository.VehicleRepository;
import com.example.practice.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
     private final VehicleRepository vehicleRepository;

     // Constructor to initialize the VehicleRepository
     VehicleServiceImpl(VehicleRepository vehicleRepository) {
          this.vehicleRepository = vehicleRepository;
     }

     /**
      * Adds a new vehicle to the system.
      * @param vehicleDto The vehicle data transfer object containing vehicle details.
      * @return The saved vehicle entity.
      * @throws VehicleExistsException if a vehicle with the same plate number already exists.
      */
     @Override
     public Vehicle addVehicle(VehicleDto vehicleDto) {
          Optional<Vehicle> vehicleExists = vehicleRepository.findByPlateNumber(vehicleDto.plateNumber());

          // Check if the vehicle already exists
          if (vehicleExists.isPresent()) {
               throw new VehicleExistsException("Vehicle exists");
          }

          // Create a new vehicle entity and save it
          Vehicle vehicle = Vehicle.builder()
                    .plateNumber(vehicleDto.plateNumber())
                    .capacity(vehicleDto.capacity())
                    .arrivalTime(LocalTime.now())
                    .build();
          return vehicleRepository.save(vehicle);
     }

     /**
      * Retrieves all vehicles in the system.
      * @return A list of all vehicles.
      */
     @Override
     public List<Vehicle> getAllVehicles() {
          return vehicleRepository.findAll();
     }

     /**
      * Adds a vehicle to the queue upon arrival.
      * @param vehicleDto The vehicle data transfer object containing vehicle details.
      * @return The updated vehicle entity.
      * @throws VehicleExistsException if the vehicle does not exist.
      * @throws VehicleStillInQueueException if the vehicle is still in the queue.
      */
     @Override
     public Vehicle addVehicleToQueueOnArrival(VehicleDto vehicleDto) {
          Optional<Vehicle> vehicleExists = vehicleRepository.findByPlateNumber(vehicleDto.plateNumber());

          // Check if the vehicle exists
          if (!vehicleExists.isPresent()) {
               throw new VehicleExistsException("Vehicle does not exist");
          }

          // Check if the vehicle is still in the queue
          if (!vehicleExists.get().isDeparted()) {
               throw new VehicleStillInQueueException("Vehicle is still in queue");
          }

          // Enqueuing a vehicle when it arrives at it's destination
          // Update the vehicle's arrival time, clear passengers, and mark it as not departed
          vehicleExists.get().setArrivalTime();
          vehicleExists.get().getPassangers().clear();
          vehicleExists.get().setDeparted(false);

          // Save the updated vehicle
          return vehicleRepository.save(vehicleExists.get());
     }

     /**
      * Retrieves the top vehicle in the queue based on arrival time. The earliest vehicle
      * @return The top vehicle in the queue.
      */
     @Override
     public Vehicle getTopVehicle() {
          return vehicleRepository.findFirstByDepartedFalseOrderByArrivalTimeAsc();
     }

     /**
      * Retrieves all vehicles currently in the queue.
      * @return A list of vehicles in the queue.
      * @throws NoVehiclesException if no vehicles are in the queue.
      */
     @Override
     public List<VehicleDto> getVehiclesInQueue() {
          List<VehicleDto> vehiclesInQueue = vehicleRepository.findByDepartedFalse();

          // Check if the queue is empty
          if (vehiclesInQueue.isEmpty()) {
               throw new NoVehiclesException("No vehicles in queue");
          }
          return vehiclesInQueue;
     }

     /**
      * Retrieves all vehicles currently travelling.(The vehicles that were dequeued)
      -> They will be enqueued to the list when they reach their desination i.e the queue in that destinatin
      (I'm just focusing on one location)
      * @return A list of vehicles that are travelling.
      * @throws NoVehiclesTravellingException if no vehicles are travelling.
      */
     @Override
     public List<Vehicle> getVehiclesTravelling() {
          List<Vehicle> vehiclesTravelling = vehicleRepository.findByDepartedTrue();

          // Check if there are no vehicles travelling
          if (vehiclesTravelling.isEmpty()) {
               throw new NoVehiclesTravellingException("No vehicles travelling");
          }
          return vehiclesTravelling;
     }
}
