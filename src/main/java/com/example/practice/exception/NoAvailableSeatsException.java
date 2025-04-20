package com.example.practice.exception;

public class NoAvailableSeatsException extends RuntimeException{
     public NoAvailableSeatsException (String message){
          super(message);
     }
}
