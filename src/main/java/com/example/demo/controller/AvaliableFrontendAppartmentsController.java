package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Apartments;
import com.example.demo.entity.BookingRequests;
import com.example.demo.entity.Residents;
import com.example.demo.service.ApartmentsService;
import com.example.demo.service.BookingRequestsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/front-end/apartments")
public class AvaliableFrontendAppartmentsController {

    @Autowired
    private ApartmentsService apartmentService;
    
    @Autowired
    private BookingRequestsService bookingRequestService;
    
    @Autowired
    private HttpSession session;

    // Show available apartments
    @GetMapping("/available-apartments")
    public String availableApartments(Model model) {
        List<Apartments> apartments = apartmentService.getAllApartments();
        model.addAttribute("apartments", apartments);

        // Check for success message
        String message = (String) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }

        return "available-apartments"; 
    }

    // Handle booking request
    @PostMapping("/bookApartment")
    public String bookApartment(@ModelAttribute BookingRequests bookingRequest, RedirectAttributes redirectAttributes) {
        Residents resident = (Residents) session.getAttribute("residents");
        if (resident == null) {
            return "redirect:/front-end/apartments/available-apartments";
        }

        bookingRequest.setResidentId(resident.getId());
        bookingRequest.setStatus("PENDING");
        bookingRequestService.addbookings(bookingRequest);

        // Update apartment availability
        Optional<Apartments> apartment = apartmentService.getApartmentsById(bookingRequest.getApartmentId());
        apartment.ifPresent(ap -> {
            ap.setIsAvailable(false);
            apartmentService.updateApartments(ap);
        });

        // Optional flash message
        session.setAttribute("message", "Your booking request has been submitted and is pending admin approval.");

        return "redirect:/front-end/apartments/bookingform";
    }

    // Show booking confirmation form
    @GetMapping("/bookingform")
    public String bookingForm(Model model, HttpSession session) {
        Residents resident = (Residents) session.getAttribute("residents");

        if (resident == null) {
            return "login"; 
        }

        int residentId = resident.getId();
        BookingRequests booking = bookingRequestService.getLatestBookingByResidentId(residentId);
        model.addAttribute("booking", booking);

        Optional<Apartments> apartmentOpt = apartmentService.getApartmentsById(booking.getApartmentId());
        apartmentOpt.ifPresent(apartment -> model.addAttribute("apartment", apartment));

        return "bookingform";
    }
    
  




}
