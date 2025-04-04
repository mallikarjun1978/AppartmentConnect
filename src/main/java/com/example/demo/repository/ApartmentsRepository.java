package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Apartments;

@Service
public interface ApartmentsRepository extends JpaRepository<Apartments, Integer> {

	long countByIsAvailableTrue();

}
