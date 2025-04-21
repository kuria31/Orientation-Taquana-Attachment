package com.example.practice.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.practice.dto.ExceptionDto;

/**
 * Global exception handler for managing application-wide exceptions.
 * Provides custom responses for specific exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

     /**
      * Handles VehicleExistsException.
      *
      * @param exception The exception thrown when a vehicle already exists.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the exception details.
      */
     @ExceptionHandler(VehicleExistsException.class)
     public ResponseEntity<Object> handleVehicleExistsException(VehicleExistsException exception, WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     /**
      * Handles NoAvailableSeatsException.
      *
      * @param exception The exception thrown when no seats are available.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the exception details.
      */
     @ExceptionHandler(NoAvailableSeatsException.class)
     public ResponseEntity<Object> handleNoAvailableSeatsException(NoAvailableSeatsException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     /**
      * Handles NoVehiclesException.
      *
      * @param exception The exception thrown when no vehicles are available.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the exception details.
      */
     @ExceptionHandler(NoVehiclesException.class)
     public ResponseEntity<Object> handleNoVehiclesException(NoVehiclesException exception, WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     /**
      * Handles VehicleStillInQueueException.
      *
      * @param exception The exception thrown when a vehicle is still in the queue.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the exception details.
      */
     @ExceptionHandler(VehicleStillInQueueException.class)
     public ResponseEntity<Object> handleVehicleStillInQueueException(VehicleStillInQueueException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     /**
      * Handles NoVehiclesTravellingException.
      *
      * @param exception The exception thrown when no vehicles are travelling.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the exception details.
      */
     @ExceptionHandler(NoVehiclesTravellingException.class)
     public ResponseEntity<Object> handleNoVehiclesTravellingException(NoVehiclesTravellingException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     /**
      * Handles validation errors for method arguments.
      *
      * @param ex The exception thrown when validation fails.
      * @param headers HTTP headers.
      * @param status HTTP status code.
      * @param request The web request that caused the exception.
      * @return A ResponseEntity containing the validation error details.
      */
     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
               HttpHeaders headers, HttpStatusCode status, WebRequest request) {
           List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream().map(
               DefaultMessageSourceResolvable::getDefaultMessage
           ).collect(Collectors.toList());

           String errorMessage = String.join(" || ", errorMessages);

           ExceptionDto response = new ExceptionDto(errorMessage, String.format("Error(s) found: %d", errorMessages.size()), LocalDateTime.now());

           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }
}
