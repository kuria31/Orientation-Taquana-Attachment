package com.example.practice.exception;

public class NoVehiclesTravellingException extends RuntimeException{
     public NoVehiclesTravellingException(String message){
          super(message);
     }
}
