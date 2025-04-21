package com.example.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dto.PassangerDto;
import com.example.practice.model.Passanger;
import com.example.practice.service.PassangerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing passenger-related operations.
 * Provides an endpoint for adding passengers to the queue.
 */
@RestController
@RequestMapping("api/v1/passangers")
public class PassangerController {

     private final PassangerService passangerService;

     // Constructor to inject the PassangerService dependency
     PassangerController(PassangerService passangerService) {
          this.passangerService = passangerService;
     }

     /**
      * Adds a passenger to the queue.
      * The passenger is assigned to a vehicle in the order they arrived.
      *
      * @param passangerDto The passenger data transfer object containing passenger details.
      * @return The created passenger entity wrapped in a ResponseEntity.
      */
     @PostMapping
     public ResponseEntity<Object> addPassangerToQueue(@Valid @RequestBody PassangerDto passangerDto) {
          Passanger passanger = passangerService.addPassangerToQueue(passangerDto.name());
          return new ResponseEntity<>(passanger, HttpStatus.OK);
     }
}