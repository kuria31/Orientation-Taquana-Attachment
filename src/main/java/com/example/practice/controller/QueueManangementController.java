package com.example.practice.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.practice.dto.PassangerDto;
import com.example.practice.dto.VehicleDto;
import com.example.practice.exception.NoAvailableSeatsException;
import com.example.practice.exception.NoVehiclesException;
import com.example.practice.exception.NoVehiclesTravellingException;
import com.example.practice.exception.VehicleExistsException;
import com.example.practice.model.Passanger;
import com.example.practice.model.Vehicle;
import com.example.practice.service.PassangerService;
import com.example.practice.service.VehicleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


//   This the controller connected to the tymeleaf client
/**
 * Controller for managing the vehicle and passenger queue system.
 * Provides endpoints for registering vehicles, issuing tickets, and retrieving queue details.
 */
@Controller
@RequestMapping("api/queue/")
@Slf4j
public class QueueManangementController {

     private final VehicleService vehicleService;
     private final PassangerService passangerService;

     public static final String ERROR_KEY = "error";
     private static final String REDIRECT = "redirect:/api/queue/home";

     // Constructor to inject the VehicleService and PassangerService dependencies
     QueueManangementController(VehicleService vehicleService, PassangerService passangerService) {
          this.vehicleService = vehicleService;
          this.passangerService = passangerService;
     }

     // Used to Enqueue Again A dequeued System. To show that wi will be added to the rear of the queue
     // Not implemented to the front end yet.
     /**
      * Adds a vehicle to the queue.
      *
      * @param plateNumber The plate number of the vehicle.
      * @param capacity The capacity of the vehicle.
      * @param model The model to hold attributes for the view.
      * @return A redirect to the home page.
      */
     @PutMapping("/addToQueue")
     public String addVehicleToQueue(@RequestParam String plateNumber, @RequestParam int capacity, Model model) {
          VehicleDto vehicleDto = new VehicleDto(plateNumber, capacity, LocalTime.now());
          model.addAttribute("addToQueue", vehicleDto);
          return REDIRECT;
     }

     /**
      * Registers a new vehicle.
      *
      * @param vehicleDto The vehicle data transfer object containing vehicle details.
      * @param model The model to hold attributes for the view.
      * @return A redirect to the home page.
      */
     @PostMapping("/register_vehicle")
     public String addVehicle(@Valid @ModelAttribute VehicleDto vehicleDto, Model model) {
          try {
               Vehicle newVehicle = vehicleService.addVehicle(vehicleDto);
               String successMessage = newVehicle != null ? "Vehicle added successfully" : "Error adding vehicle";
               model.addAttribute("success", successMessage);
          } catch (VehicleExistsException e) {
               model.addAttribute(ERROR_KEY, e.getMessage());
          }
          return REDIRECT;
     }

     /**
      * Issues a ticket to a passenger.
      *
      * @param passangerDto The passenger data transfer object containing passenger details.
      * @param model The model to hold attributes for the view.
      * @return A redirect to the home page.
      */
     @PostMapping("/home/issue_ticket")
     public String postMethodName(@Valid @ModelAttribute PassangerDto passangerDto, Model model) {
          try {
               Passanger newPassanger = passangerService.addPassangerToQueue(passangerDto.name());
               String successMessage = newPassanger != null ? "Ticket issued successfully" : "Error issuing ticket";
               model.addAttribute("success", successMessage);
          } catch (NoVehiclesException | NoAvailableSeatsException e) {
               model.addAttribute(ERROR_KEY, e.getMessage());
          }
          return REDIRECT;
     }

     /**
      * Retrieves the active boarding vehicle and queue details.
      *
      * @param model The model to hold attributes for the view.
      * @return The home page view name.
      */
     @GetMapping("/home")
     public String findActiveBoardingVehicle(Model model) {
          try {
               // Get the active boarding vehicle
               Vehicle vehicle = vehicleService.getTopVehicle();
               model.addAttribute("vehicle", vehicle);

               // Get the vehicles in the queue
               List<VehicleDto> vehiclesInQueue = vehicleService.getVehiclesInQueue();
               model.addAttribute("vehiclesInQueue", vehiclesInQueue);

               // Get the vehicles currently travelling
               List<Vehicle> vehiclesTravelling = vehicleService.getVehiclesTravelling();
               model.addAttribute("vehiclesTravelling", vehiclesTravelling);
          } catch (NoVehiclesException | NoVehiclesTravellingException e) {
               log.error(e.getMessage());
               model.addAttribute(ERROR_KEY, e.getMessage());
          }
          return "home";
     }
}