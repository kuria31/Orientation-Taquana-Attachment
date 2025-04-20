package com.example.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

@Controller
@RequestMapping("api/queue/")
@Slf4j
public class QueueManangementController {
     private final VehicleService vehicleService;
     private final PassangerService passangerService;

     public static final String ERROR_KEY = "error";

     QueueManangementController(VehicleService vehicleService, PassangerService passangerService) {
          this.vehicleService = vehicleService;
          this.passangerService = passangerService;
     }

     @PostMapping("/register_vehicle")
     public String addVehicle(@Valid @ModelAttribute VehicleDto vehicleDto, Model model) {
          try {
               Vehicle newVehicle = vehicleService.addVehicle(vehicleDto);
               String successMessage = newVehicle != null ? "Vehicle added sucessfully" : "Error adding Vehicle";

               model.addAttribute("success", successMessage);
          } catch (VehicleExistsException e) {
               model.addAttribute(ERROR_KEY, e.getMessage());
          }
          return "redirect:/api/queue/home";
     }

     @PostMapping("/home/issue_ticket")
     public String postMethodName(@Valid @ModelAttribute PassangerDto passangerDto, Model model) {
          try {
               Passanger newPassanger = passangerService.addPassangerToQueue(passangerDto.name());
               String successMessage = newPassanger != null ? "Ticket issued sucessfullt" : "Error issuing ticket";

               model.addAttribute("success", successMessage);
          } catch (NoVehiclesException | NoAvailableSeatsException e) {
               model.addAttribute(ERROR_KEY, e.getMessage());
          }

          return "redirect:/api/queue/home";
     }

     @GetMapping("/home")
     public String findActiveBoardingVehicle(Model model) {
          // The active boading vehcles
          Vehicle vehicle = vehicleService.getTopVehicle();

          try {

               // Getting the vechices in queue
               List<VehicleDto> vehiclesInQueue = vehicleService.getVehiclesInQueue();
               model.addAttribute("vehiclesInQueue", vehiclesInQueue);

               // Getting vehicles travelleing
               List<Vehicle> vehiclesTravelling = vehicleService.getVehiclesTravelling();
               model.addAttribute("vehiclesTravelling", vehiclesTravelling);

          } catch (NoVehiclesException e) {
               log.error(e.getMessage());
               model.addAttribute(ERROR_KEY, e.getMessage());
          } catch (NoVehiclesTravellingException e) {
               log.error(e.getMessage());
               model.addAttribute(ERROR_KEY, e.getMessage());
          }

          // Car information
          model.addAttribute("vehicle", vehicle);

          return "home";
     }

}
