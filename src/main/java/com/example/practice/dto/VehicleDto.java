package com.example.practice.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for Vehicle.
 * Used to transfer vehicle-related data between layers.
 *
 * @param plateNumber The unique plate number of the vehicle (cannot be blank).
 * @param capacity    The maximum number of passengers the vehicle can hold
 *                    (cannot be null).
 * @param arrivalTime The time when the vehicle arrived (optional).
 */
public record VehicleDto(
          @NotBlank(message = "Vehicle plate Number cannot be null") String plateNumber,
          @NotNull(message = "Capacity cannot be null") int capacity,
          LocalTime arrivalTime) {

}
