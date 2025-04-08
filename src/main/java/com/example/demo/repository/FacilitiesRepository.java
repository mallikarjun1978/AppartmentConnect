package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facilities;

@Service
public interface FacilitiesRepository extends JpaRepository<Facilities, Integer> {
    // Custom query methods (if needed)
    List<Facilities> findByIsActiveTrue();
}

