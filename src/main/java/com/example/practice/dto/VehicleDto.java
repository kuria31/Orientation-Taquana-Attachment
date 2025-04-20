package com.example.practice.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleDto(
          @NotBlank(message = "Vehicle plate Number cannot be null") String plateNumber,
          @NotNull(message = "Capacity cannot be null") int capacity,
          LocalTime arrivalTime) {

}
