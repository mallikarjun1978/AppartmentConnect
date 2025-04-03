package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private HttpSession session; // Inject HttpSession
	

    @GetMapping("/available-apartments")
    public String availableApartments(Model model, HttpSession session) {
        List<Apartments> apartments = apartmentService.getAllApartments();
        model.addAttribute("apartments", apartments);

        // Check for success message
        String message = (String) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message"); // Remove after displaying
        }

        return "available-apartments"; 
    }

    @PostMapping("/bookApartment")
    public String bookApartment(@ModelAttribute BookingRequests bookingRequest, RedirectAttributes redirectAttributes) {
        System.out.println("okk");
        Residents resident = (Residents) session.getAttribute("residents");
		
    	Integer residentId = resident.getId();  
    	System.out.println("residentId: " + residentId);
        if (residentId == null) {
            return "redirect:/front-end/apartments/available-apartments";
        }

        bookingRequest.setResidentId(residentId);
        bookingRequest.setStatus("PENDING");

        bookingRequestService.addbookings(bookingRequest);
        
        // 1. get appartment id
        int apartmentId = bookingRequest.getApartmentId();
        
        //2. update appartment availability
        Optional<Apartments> apartment = apartmentService.getApartmentsById(apartmentId);
        if(apartment.isPresent()) {
        	Apartments ap = apartment.get();
        	ap.setIsAvailable(false);
        	apartmentService.updateApartments(ap);
        }
        

        // Add flash attribute for success message
        redirectAttributes.addFlashAttribute("message", "Your booking request has been submitted and is pending admin approval.");

        return "redirect:/front-end/apartments/available-apartments";
    }
}
