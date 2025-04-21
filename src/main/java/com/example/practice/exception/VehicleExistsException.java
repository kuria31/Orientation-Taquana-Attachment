package com.example.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a vehicle with the same plate number already exists.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleExistsException extends RuntimeException {
     public VehicleExistsException(String message) {
          super(message);
     }
}
