package com.example.practice.exception;

/**
 * Exception thrown when no seats are available in a vehicle.
 */
public class NoAvailableSeatsException extends RuntimeException {
     public NoAvailableSeatsException(String message) {
          super(message);
     }
}
