package com.example.practice.service.impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.practice.exception.NoAvailableSeatsException;
import com.example.practice.exception.NoVehiclesException;
import com.example.practice.model.Passanger;
import com.example.practice.model.Vehicle;
import com.example.practice.repository.PassangerRepository;
import com.example.practice.repository.VehicleRepository;
import com.example.practice.service.PassangerService;

import jakarta.transaction.Transactional;

@Service
public class PassangerServiceImpl implements PassangerService {
     private final PassangerRepository passangerRepository;
     private final VehicleRepository vehicleRepository;

     // Constructor to initialize the repositories
     PassangerServiceImpl(PassangerRepository passangerRepository, VehicleRepository vehicleRepository) {
          this.passangerRepository = passangerRepository;
          this.vehicleRepository = vehicleRepository;
     }

     @Override
     @Transactional
     public Passanger addPassangerToQueue(String name) {
          /*Fetch the list of vehicles currently in the queue (through the use of timestaps) 
          The vehicle that arrived first will have the earliest timestamp
          */
          List<Vehicle> vehicleQueue = vehicleRepository.findQueuedVehiclesUsingTimestanps();

          // If no vehicles are available, throw an exception
          if (vehicleQueue.isEmpty()) {
               throw new NoVehiclesException("No vehicles available");
          }

          // Iterate through the vehicles in the queue
          for (Vehicle vehicle : vehicleQueue) {
               // Check if the vehicle has available seats 
               if (vehicle.hasAvailableSeats()) {
                    // Create a new passenger and update the passanger and the vehicle
                    Passanger passanger = new Passanger();
                    passanger.setName(name);
                    passanger.setSeatNumber(vehicle.getPassangers().size() + 1); // Assign seat number
                    passanger.setVehicle(vehicle); // Associate the passenger with the vehicle
                    vehicle.getPassangers().add(passanger); // Add the passenger to the vehicle's list
                    passanger.setArrivalTime(LocalTime.now());

                    // If the vehicle is now full, mark it as departed(it is dequeued)
                    if (!vehicle.hasAvailableSeats()) {
                         vehicle.setDeparted(true);
                    }

                    // Save the passenger to the repository and return it
                    return passangerRepository.save(passanger);
               }
          }

          // If no vehicle with available seats is found, throw an exception
          throw new NoAvailableSeatsException("No vehicle available with seats");
     }
}
