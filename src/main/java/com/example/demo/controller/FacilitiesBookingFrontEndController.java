package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Facilities;
import com.example.demo.entity.FacilitiesBooking;
import com.example.demo.entity.Residents;
import com.example.demo.service.FacilitiesBookingService;
import com.example.demo.service.FacilitiesService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/front-end/facilitiesbooking")

public class FacilitiesBookingFrontEndController {
	
	
	@Autowired
		private FacilitiesService facilitiesservice;
	@Autowired
	private FacilitiesBookingService facilitiesbookingservice;
	 @Autowired
		private HttpSession session; 
	 
	 @GetMapping("/available-facilities")
	 public ModelAndView availableFacilities(Model model, HttpSession session) {
	     // Fetch all available facilities from the service
	     List<Facilities> facilities = facilitiesservice.getAllFacilities();
	     model.addAttribute("facilities", facilities);

	     // Check for a success message stored in the session
	     String message = (String) session.getAttribute("message");
	     if (message != null) {
	         model.addAttribute("message", message);
	         session.removeAttribute("message"); // Remove after displaying
	     }
	     ModelAndView view = new ModelAndView("Book_A_Facility");
	     return view; // The view name (Thymeleaf template)
	 }
	 @PostMapping("/bookFacility")
	 public ModelAndView bookFacility(@ModelAttribute FacilitiesBooking facilitiesBooking, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
	     System.out.println("Booking facility...");

	     // Retrieve the logged-in resident from the session
	     Residents resident = (Residents) session.getAttribute("residents");

	     Integer residentId = resident != null ? resident.getId() : null;
	    // System.out.println("Resident ID: " + residentId);
	    

	     // Set resident ID and initial booking status
	     facilitiesBooking.setResidentId(residentId);
	     facilitiesBooking.setBooking_status("PENDING");
	     
	     
	     int facilityID = Integer.parseInt(request.getParameter("facilityId").toString());
	     System.out.println("facilityID=" + facilityID);
	     
	     Optional<Facilities> facility = facilitiesservice.getFacilityById(facilityID);
	     if(facility.isPresent()) {
	    	 Facilities fob = facility.get();
	    	 facilitiesBooking.setFacility_name(fob.getFacilityName());
	    	 facilitiesBooking.setAvailable_time(fob.getAvailableTime());
	     }
	     
	     System.out.println("facilitiesBooking= "+facilitiesBooking);

	     // Add booking request to the database
	     facilitiesbookingservice.addfacilitiesBooking(facilitiesBooking);

	     // 1. Get facility ID from the booking request
	     int facilityId = facilitiesBooking.getFacility_bookingId();

	     // 2. Update the facility's availability after booking
	     
	     ModelAndView view = new ModelAndView("redirect:/front-end/facilitiesbooking/available-facilities");
	     // Add a flash attribute to show a success message to the user
	     redirectAttributes.addFlashAttribute("message", "Your booking request has been submitted and is pending approval.");

	     // Redirect back to the available facilities page
	     return view;
	 }
	 
	 @GetMapping("/history")
	 public ModelAndView viewMyBookings(Model model, HttpSession session) {
	     // Retrieve the logged-in resident
	     Residents resident = (Residents) session.getAttribute("residents");
	     if (resident == null) {
	         // Handle unauthenticated access (redirect to login or show error)
	         return new ModelAndView("redirect:/login"); 
	     }

	     // Get resident ID
	     Integer residentId = resident.getId();

	     // Fetch bookings by resident ID
	     List<FacilitiesBooking> bookings = facilitiesbookingservice.getBookingsByResidentId(residentId);

	     // Add bookings to the model
	     model.addAttribute("bookings", bookings);

	     return new ModelAndView("History"); // This is your Thymeleaf HTML view
	 }



}
