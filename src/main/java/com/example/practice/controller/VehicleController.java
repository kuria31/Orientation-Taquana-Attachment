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

/**
 * REST controller for managing vehicle-related operations.
 * Provides endpoints for adding, retrieving, and updating vehicles.
 */
@RestController
@RequestMapping("api/v1/vehicle")
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    // Constructor to inject the VehicleService dependency
    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Adds a new vehicle to the system.
     *
     * @param vehicleDto The vehicle data transfer object containing vehicle details.
     * @return The saved vehicle entity wrapped in a ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        Vehicle newVehicle = vehicleService.addVehicle(vehicleDto);
        return ResponseEntity.ok(newVehicle);
    }

    /**
     * Retrieves all vehicles in the system.
     *
     * @return A list of all vehicles wrapped in a ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    /**
     * Adds a vehicle to the queue upon arrival.
     *
     * @param vehicleDto The vehicle data transfer object containing vehicle details.
     * @return The updated vehicle entity wrapped in a ResponseEntity.
     */
    @PutMapping
    public ResponseEntity<Vehicle> addVehicleToQueue(@RequestBody VehicleDto vehicleDto) {
        return ResponseEntity.ok(vehicleService.addVehicleToQueueOnArrival(vehicleDto));
    }

    /**
     * Retrieves all vehicles currently in the queue.
     *
     * @return A list of vehicles in the queue wrapped in a ResponseEntity.
     */
    @GetMapping("/in_queue")
    public ResponseEntity<List<VehicleDto>> getVehiclesInQueue() {
        return ResponseEntity.ok(vehicleService.getVehiclesInQueue());
    }

    /**
     * Retrieves all vehicles currently travelling.
     *
     * @return A list of vehicles that are travelling wrapped in a ResponseEntity.
     */
    @GetMapping("/travelling")
    public ResponseEntity<List<Vehicle>> getVehiclesTravelling() {
        return ResponseEntity.ok(vehicleService.getVehiclesTravelling());
    }

    /**
     * Retrieves the top vehicle in the queue based on arrival time.
     *
     * @return The top vehicle in the queue wrapped in a ResponseEntity.
     */
    @GetMapping("/top")
    public ResponseEntity<Vehicle> getTopVehicle() {
        return ResponseEntity.ok(vehicleService.getTopVehicle());
    }
}