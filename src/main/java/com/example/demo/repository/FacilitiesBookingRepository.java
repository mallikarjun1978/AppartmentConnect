package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FacilitiesBooking;

@Service
public interface FacilitiesBookingRepository extends JpaRepository<FacilitiesBooking,Integer>{
	
}
