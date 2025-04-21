package com.example.practice.exception;

/**
 * Exception thrown when no vehicles are currently travelling.
 */
public class NoVehiclesTravellingException extends RuntimeException {
     public NoVehiclesTravellingException(String message) {
          super(message);
     }
}
