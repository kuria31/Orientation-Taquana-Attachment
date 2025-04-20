package com.example.practice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dto.VehicleDto;
import com.example.practice.model.Vehicle;
import com.example.practice.service.VehicleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/v1/vehicle")
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle newVehicle = vehicleService.addVehicle(vehicleDto);

        return ResponseEntity.ok(newVehicle);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping
    public ResponseEntity<Vehicle> addVehicleToQueue(@RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.ok(vehicleService.addVehicleToQueueOnArrival(vehicleDto));
    }

    @GetMapping("/in_queue")
    public ResponseEntity<List<VehicleDto>> getVehiclesInQueue() {
        return ResponseEntity.ok(vehicleService.getVehiclesInQueue());
    }
    @GetMapping("/travelling")
    public ResponseEntity<List<Vehicle>> getVehiclesTravelling() {
        return ResponseEntity.ok(vehicleService.getVehiclesTravelling());
    }

    @GetMapping("/top")
    public ResponseEntity<Vehicle> getTopVehicle() {
        return ResponseEntity.ok(vehicleService.getTopVehicle());
    }
    
    


}
