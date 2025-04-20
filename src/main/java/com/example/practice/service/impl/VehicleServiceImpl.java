package com.example.practice.service.impl;

import java.time.LocalDateTime;
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

     VehicleServiceImpl(VehicleRepository vehicleRepository) {
          this.vehicleRepository = vehicleRepository;
     }
     
     @Override
     public Vehicle addVehicle(VehicleDto vehicleDto) {
          Optional<Vehicle> vehicleExists = vehicleRepository.findByPlateNumber(vehicleDto.plateNumber());

          if (vehicleExists.isPresent()) {
               throw new VehicleExistsException("Vehicle exists");
          }
          Vehicle vehicle = Vehicle.builder()
                    .plateNumber(vehicleDto.plateNumber())
                    .capacity(vehicleDto.capacity())
                    .arrivalTime(LocalTime.now())
                    .build();
          return vehicleRepository.save(vehicle);
     }

     @Override
     public List<Vehicle> getAllVehicles() {
          return vehicleRepository.findAll();
     }

     @Override
     public Vehicle addVehicleToQueueOnArrival(VehicleDto vehicleDto) {

          Optional<Vehicle> vehicleExists = vehicleRepository.findByPlateNumber(vehicleDto.plateNumber());

          // Check if the vehicle exists
          if (!vehicleExists.isPresent()) {
               throw new VehicleExistsException("Vehicle does not exists");
          }
          
          // Check if the vehcle is still in queue
          if (!vehicleExists.get().isDeparted()) throw new VehicleStillInQueueException("Vehicle is still in queue");

          // Update the vehicles
          vehicleExists.get().setArrivalTime();
          vehicleExists.get().getPassangers().clear();
          vehicleExists.get().setDeparted(false);

          // Save the vehcles
          return vehicleRepository.save(vehicleExists.get());
     }

     @Override
     public Vehicle getTopVehicle() {
          return vehicleRepository.findFirstByDepartedFalseOrderByArrivalTimeAsc();
     }

     @Override
     public List<VehicleDto> getVehiclesInQueue() {
          List<VehicleDto> vehiclesInQueue = vehicleRepository.findByDepartedFalse();

          if (vehiclesInQueue.isEmpty()){
               throw new NoVehiclesException("No vehicles in queue");
          }
          return vehiclesInQueue;
     }

     @Override
     public List<Vehicle> getVehiclesTravelling() {
          List<Vehicle> vehiclesTravelling = vehicleRepository.findByDepartedTrue();

          if (vehiclesTravelling.isEmpty()){
               throw new NoVehiclesTravellingException("No vehicles in travelling");
          }
          return vehiclesTravelling;
     }
}
