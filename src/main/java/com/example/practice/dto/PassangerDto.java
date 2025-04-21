package com.example.practice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for Passenger.
 * Used to transfer passenger-related data between layers.
 *
 * @param name The name of the passenger (cannot be blank).
 */
public record PassangerDto(
          @NotBlank(message = "Name cannot be blank") String name) {

}
