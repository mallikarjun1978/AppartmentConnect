package com.example.demo.repository;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> fffaefc7719c5f4e6311d299bf1a6f5517c1263b

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MaintenanceRequest;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Integer>{

<<<<<<< HEAD
	List<MaintenanceRequest> findByStatus(String status);  // To get all pending requests
    long countByStatus(String status);  // To count pending requests
=======
	List<MaintenanceRequest> findByResidentId(int loggedInResidentId);

	MaintenanceRequest findTopByOrderByCreatedAtDesc();
	

	Optional<MaintenanceRequest> findById(Long Id);

	MaintenanceRequest findTopByResidentIdOrderByCreatedAtDesc(int residentId);

>>>>>>> fffaefc7719c5f4e6311d299bf1a6f5517c1263b
}
