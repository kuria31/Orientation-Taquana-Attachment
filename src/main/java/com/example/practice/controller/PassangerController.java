package com.example.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practice.dto.PassangerDto;

import com.example.practice.model.Passanger;
import com.example.practice.service.PassangerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/passangers")
public class PassangerController {
     private final PassangerService passangerService;

     PassangerController(PassangerService passangerService){
          this.passangerService = passangerService;
     }

     @PostMapping("/")
     public ResponseEntity<Object> addPassangerToQueue(@Valid @RequestBody PassangerDto passangerDto) {
          Passanger passanger = passangerService.addPassangerToQueue(passangerDto.name());
         return new ResponseEntity<>(passanger, HttpStatus.OK);
     }

     
}
