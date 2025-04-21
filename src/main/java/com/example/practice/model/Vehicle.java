package com.example.practice.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Vehicle.
 * A vehicle can have multiple passengers and is associated with a queue system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "plateNuber", nullable = false)
     private String plateNumber; // Unique identifier for the vehicle

     @Column(name = "capacity", nullable = false)
     private int capacity; // Maximum number of passengers the vehicle can hold

     private boolean departed; // Indicates whether the vehicle has departed

     private LocalTime arrivalTime; // Time when the vehicle arrived in the queue

     @JsonManagedReference
     @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Passanger> passangers; // List of passengers associated with the vehicle

     /**
      * Checks if the vehicle has available seats.
      *
      * @return true if there are available seats, false otherwise.
      */
     public boolean hasAvailableSeats() {
          return passangers != null && passangers.size() < capacity;
     }

     /**
      * Sets the arrival time of the vehicle to the current time.
      */
     public void setArrivalTime() {
          this.arrivalTime = LocalTime.now();
     }

     /**
      * Calculates the number of remaining seats in the vehicle.
      * 
      * @return The number of remaining seats.
      */
     @Transient
     public int getRemainingSeats() {
          return passangers != null ? capacity - passangers.size() : capacity;
     }

}
