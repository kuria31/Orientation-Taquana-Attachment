package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.model.Passanger;

/**
 * Repository interface for managing passenger-related database operations.
 * Extends JpaRepository to provide CRUD operations for the Passanger entity.
 */
@Repository
public interface PassangerRepository extends JpaRepository<Passanger, Long> {
    
}
