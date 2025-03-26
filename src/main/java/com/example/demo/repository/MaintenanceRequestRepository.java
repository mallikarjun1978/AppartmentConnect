package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MaintenanceRequest;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Integer>{

	List<MaintenanceRequest> findByResidentId(int loggedInResidentId);

	MaintenanceRequest findTopByOrderByCreatedAtDesc();
	

	Optional<MaintenanceRequest> findById(Long Id);

	MaintenanceRequest findTopByResidentIdOrderByCreatedAtDesc(int residentId);

}
