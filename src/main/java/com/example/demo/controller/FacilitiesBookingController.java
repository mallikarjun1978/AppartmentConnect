package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FacilitiesBooking;

import com.example.demo.service.FacilitiesBookingService;

@RestController
@RequestMapping("api/v1")
public class FacilitiesBookingController {
	@Autowired
	FacilitiesBookingService facilitiesbookingservice;
	
	 @PostMapping("/addFacilitiesBooking")
	 public ResponseEntity<FacilitiesBooking> addFacilitiesBooking(@RequestBody FacilitiesBooking facilitiesBooking) {
	        facilitiesbookingservice.addfacilitiesBooking(facilitiesBooking);
	        return new ResponseEntity<FacilitiesBooking>(facilitiesBooking,HttpStatus.CREATED);
	    }
	 @GetMapping("/allFacilitesBooking")
	    public ResponseEntity<List<FacilitiesBooking>> getAllFacilitiesBookings() {
	        List<FacilitiesBooking> facilitiesBookings = facilitiesbookingservice.getAllFacilitiesBooking();
	        return ResponseEntity.ok(facilitiesBookings);
	    }
	 
	 @GetMapping(value = "/getFacilitiesBooking/{facility_bookingId}")
		public ResponseEntity<FacilitiesBooking> getFacilities(@PathVariable("facility_bookingId") int facility_bookingId) {
		 FacilitiesBooking facilitiesbooking = facilitiesbookingservice.getfacilitesBookingbyId(facility_bookingId);

			if (facilitiesbooking != null) {
				return ResponseEntity.ok(facilitiesbooking);

			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping(value = "/getFacilitiesBooking1")
		public ResponseEntity<Object> getResidents1(@PathVariable("facility_bookingId") int facility_bookingId) {
			FacilitiesBooking facilitiesbooking;

			if (facilitiesbookingservice.isFacilitesBookingExsit(facility_bookingId)) {
				facilitiesbooking = facilitiesbookingservice.getfacilitesBookingbyId(facility_bookingId);

			} else {
				facilitiesbooking = null;
			}
			ResponseEntity<Object> entity = new ResponseEntity<>(facilitiesbooking, HttpStatus.OK);
			return entity;
		}

	    @DeleteMapping("/deleteFacilitesBooking/{facility_bookingId}")
	    public ResponseEntity<Boolean> deleteFacilitiesBooking(@PathVariable("facility_bookingId") int facility_bookingId) {
	       
	        boolean flag;
	        if (facilitiesbookingservice.isFacilitesBookingExsit(facility_bookingId)) {
	        	flag = facilitiesbookingservice.deleteFacilitiesBooking(facility_bookingId);
	        } else {
	        	flag = false;
	        }
	        return new ResponseEntity<>(flag, HttpStatus.OK);
	    }
	    
	    @PutMapping("/updateFacilitiesBooking/{facility_bookingId}")
		public ResponseEntity<Boolean> updateFacilitiesBooking(@PathVariable("facility_bookingId") int facility_bookingId,
				@RequestBody FacilitiesBooking facilitiesbooking) {
			boolean flag;
			if (facilitiesbookingservice.isFacilitesBookingExsit(facility_bookingId)) {
				flag = facilitiesbookingservice.updateFacilitiesBooking(facilitiesbooking);

			} else {
				flag = false;
			}
			return new ResponseEntity<>(flag, HttpStatus.OK);
		}
}
