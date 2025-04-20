package com.example.practice.dto;

import java.time.LocalDateTime;


public record ExceptionDto(String message, String description, LocalDateTime timestamp) {

}
