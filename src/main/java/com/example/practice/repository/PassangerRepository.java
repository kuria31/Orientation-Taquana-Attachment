package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.model.Passanger;

@Repository
public interface PassangerRepository extends JpaRepository<Passanger, Long> {

}
