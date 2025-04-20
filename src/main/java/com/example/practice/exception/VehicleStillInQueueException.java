package com.example.practice.exception;

public class VehicleStillInQueueException extends RuntimeException{
     public VehicleStillInQueueException (String message){
          super(message);
     }
}
