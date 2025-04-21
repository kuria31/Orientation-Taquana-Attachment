package com.example.practice.service;

import com.example.practice.model.Passanger;

/**
 * Service interface for managing passenger-related operations.
 */
public interface PassangerService {

     /**
      * Adds a passenger to the queue.
      * The passenger will be assigned to the first available vehicle in the queue.
      *
      * @param name The name of the passenger.
      * @return The created passenger entity.
      */
     Passanger addPassangerToQueue(String name);
}
