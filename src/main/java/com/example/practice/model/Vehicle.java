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
     private String plateNumber;
     @Column(name = "capacity", nullable = false)
     private int capacity;

     private boolean departed;

     private LocalTime arrivalTime;


     @JsonManagedReference
     @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Passanger> passangers;

     public boolean hasAvailableSeats() {
          return passangers != null && passangers.size() < capacity;
     }

     public void setArrivalTime() {
          this.arrivalTime = LocalTime.now();
     }

     @Transient
     public int getRemainingSeats() {
          return passangers != null ? capacity - passangers.size() : capacity;
     }

}
