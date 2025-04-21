package com.example.practice.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for Exception Details.
 * Used to customize error responses for exceptions.
 *
 * @param message     The error message describing the exception.
 * @param description Additional details about the exception (e.g., path or
 *                    cause).
 * @param timestamp   The time when the exception occurred.
 */
public record ExceptionDto(String message, String description, LocalDateTime timestamp) {

}
