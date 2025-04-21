package com.example.practice.exception;

/**
 * Exception thrown when no vehicles are available in the queue.
 */
public class NoVehiclesException extends RuntimeException {
     public NoVehiclesException(String message) {
          super(message);
     }
}
