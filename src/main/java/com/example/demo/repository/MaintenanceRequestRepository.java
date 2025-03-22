package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MaintenanceRequest;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Integer>{

	List<MaintenanceRequest> findByStatus(String status);  // To get all pending requests
    long countByStatus(String status);  // To count pending requests
}
