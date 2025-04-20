package com.example.practice.service.impl;

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
public class PassangerServiceImpl implements PassangerService{
     private final PassangerRepository passangerRepository;
     private final VehicleRepository vehicleRepository;

     PassangerServiceImpl(PassangerRepository passangerRepository, VehicleRepository vehicleRepository){
          this.passangerRepository = passangerRepository;
          this.vehicleRepository = vehicleRepository;
     }

     @Override
     @Transactional
     public Passanger addPassangerToQueue(String name) {
          List<Vehicle> vehicleQueue = vehicleRepository.findQueuedVehiclesUsingTimestanps();

          if (vehicleQueue.isEmpty()){
               throw new NoVehiclesException("No vehicles available");
          }
          for (Vehicle vehicle : vehicleQueue) {
               if(vehicle.hasAvailableSeats()){
                    Passanger passanger = new Passanger();
                    

                    passanger.setName(name);
                    passanger.setSeatNumber(vehicle.getPassangers().size() + 1);
                    passanger.setVehicle(vehicle);
                    vehicle.getPassangers().add(passanger);
                    

                    if(!vehicle.hasAvailableSeats()){
                         vehicle.setDeparted(true);
                    }
                    return passangerRepository.save(passanger);

               }
               
          }
          throw new NoAvailableSeatsException("No vehicle available with seats");
     }
}
