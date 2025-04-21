package com.example.practice.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Passenger.
 * A passenger is associated with a vehicle and has a seat number.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Passanger {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id; // Unique identifier for the passenger

     private String name; // Name of the passenger

     private int seatNumber; // Seat number assigned to the passenger


     private LocalTime arrivalTime;

     @JsonBackReference
     @ManyToOne
     @JoinColumn(name = "vehicle_id", nullable = false)
     private Vehicle vehicle; // The vehicle associated with the passenger

     /**
      * Gets the arrival time of the passenger.
      *
      * @return The current time as the arrival time.
      */

}
