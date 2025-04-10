package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FacilitiesBooking;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FacilitiesBookingRepository;
import com.example.demo.service.FacilitiesBookingService;

@Service
public class FacilitiesBookingServiceImpl implements FacilitiesBookingService {
	
	@Autowired
	FacilitiesBookingRepository facilitiesbookingRepository;

	@Override
	public void addfacilitiesBooking(FacilitiesBooking facilitiesbooking) {
		
		facilitiesbookingRepository.save(facilitiesbooking);
	}

	@Override
	public List<FacilitiesBooking> getAllFacilitiesBooking() {
		List<FacilitiesBooking> facilitiesBookingsList = facilitiesbookingRepository.findAll();
		return facilitiesBookingsList;
	}

	@Override
	public boolean isFacilitesBookingExsit(int facility_bookingId) {
		Optional<FacilitiesBooking> facilitesbooking = facilitiesbookingRepository.findById(facility_bookingId);
		FacilitiesBooking facilitesbookings;
		if(facilitesbooking.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public FacilitiesBooking getfacilitesBookingbyId(int facility_bookingId) {
		Optional<FacilitiesBooking> facilitesbooking = facilitiesbookingRepository.findById(facility_bookingId);
		FacilitiesBooking facilitesbookings;
		if(facilitesbooking.isPresent()) {
			facilitesbookings = facilitesbooking.get();
		}else {
			throw new ResourceNotFoundException("FacilitesBooking","facility_bookingId",facility_bookingId);
		}
		return facilitesbookings;
		
	}

	@Override
	public boolean deleteFacilitiesBooking(int facility_bookingId) {
		Optional<FacilitiesBooking> facilitesbooking = facilitiesbookingRepository.findById(facility_bookingId);
		FacilitiesBooking facilitesbookings;
		if(facilitesbooking.isPresent()) {
			facilitiesbookingRepository.deleteById(facility_bookingId);
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public boolean updateFacilitiesBooking(FacilitiesBooking facilitiesbooking) {
		// TODO Auto-generated method stub
		Optional<FacilitiesBooking> facilitesbooking1 = facilitiesbookingRepository.findById(facilitiesbooking.getFacility_bookingId());
		FacilitiesBooking facilitesbookings;
		if(facilitesbooking1.isPresent()) {
			facilitiesbookingRepository.save(facilitiesbooking);
			return true;
		}else {
			return false;
		}
	}


	
	

}
