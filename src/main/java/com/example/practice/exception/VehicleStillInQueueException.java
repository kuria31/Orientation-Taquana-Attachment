package com.example.practice.exception;

/**
 * Exception thrown when a vehicle is still in the queue and cannot be processed.
 */
public class VehicleStillInQueueException extends RuntimeException {
     public VehicleStillInQueueException(String message) {
          super(message);
     }
}
