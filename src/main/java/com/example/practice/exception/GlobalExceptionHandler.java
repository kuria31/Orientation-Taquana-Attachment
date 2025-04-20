package com.example.practice.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cglib.core.Local;
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

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(VehicleExistsException.class)
     public ResponseEntity<Object> handleVehicleExistsException(VehicleExistsException exception, WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(NoAvailableSeatsException.class)
     public ResponseEntity<Object> handleNoAvailableSeatsException(NoAvailableSeatsException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(NoVehiclesException.class)
     public ResponseEntity<Object> handleNoVehiclesException(NoVehiclesException exception, WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(VehicleStillInQueueException.class)
     public ResponseEntity<Object> handleVehicleStillInQueueException(VehicleStillInQueueException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

     
     @ExceptionHandler(NoVehiclesTravellingException.class)
     public ResponseEntity<Object> handleNoVehiclesTravellingException(NoVehiclesTravellingException exception,
               WebRequest request) {
          ExceptionDto response = new ExceptionDto(exception.getMessage(),
                    String.format("Path is: %s", request.getDescription(false)), LocalDateTime.now());

          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

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
