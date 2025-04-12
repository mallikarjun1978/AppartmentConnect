package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.FacilitiesBooking;

@Service
public interface FacilitiesBookingService {
	void addfacilitiesBooking(FacilitiesBooking facilitiesbooking);
	List<FacilitiesBooking> getAllFacilitiesBooking();
	List<FacilitiesBooking> getBookingsByResidentId(Integer residentId);
	boolean isFacilitesBookingExsit(int facility_bookingId);
	FacilitiesBooking getfacilitesBookingbyId(int facility_bookingId);
	boolean deleteFacilitiesBooking(int facility_bookingId);
	boolean updateFacilitiesBooking(FacilitiesBooking facilitiesbooking);
	
	
}
