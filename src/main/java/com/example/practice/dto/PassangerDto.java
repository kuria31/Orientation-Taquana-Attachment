package com.example.practice.dto;

import jakarta.validation.constraints.NotBlank;

public record PassangerDto(
     @NotBlank(message="Name cannot be blank")
     String name) {

}
