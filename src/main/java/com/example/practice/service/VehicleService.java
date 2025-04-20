package com.example.practice.service;

import java.util.List;

import com.example.practice.dto.VehicleDto;
import com.example.practice.model.Vehicle;

public interface VehicleService {
     Vehicle addVehicle(VehicleDto vehicleDto);

     List<Vehicle> getAllVehicles();

     Vehicle addVehicleToQueueOnArrival(VehicleDto vehicleDto);

     Vehicle getTopVehicle();

     List<VehicleDto> getVehiclesInQueue();
     List<Vehicle> getVehiclesTravelling();
     


}
