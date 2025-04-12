package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Residents;
import com.example.demo.service.ApartmentsService;
import com.example.demo.service.MaintenanceRequestService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/front-end/residents")
public class ResidentsFrontEndController {
	@Autowired
	private HttpSession session; // Inject HttpSession
	
	@Autowired
	private ApartmentsService apartmentService;
	
	@Autowired
	private MaintenanceRequestService maintenanceRequestService;
	
	// Map the /login endpoint to render the login.html page
    @GetMapping("/login")
    public ModelAndView login(Model model) {
    	Residents residents = new Residents();
		model.addAttribute("residents",residents);
		ModelAndView view = new ModelAndView("login");
		return view;
    }
    
   

	@GetMapping("/register")
	public ModelAndView register(Model model) {
		Residents residents = new Residents();
		model.addAttribute("residents",residents);
		ModelAndView view = new ModelAndView("ResidentRegistration");
		return view;
	} 
	
	@PostMapping("/addResident")
	public ModelAndView addResidents(@ModelAttribute("residents") Residents residents, Model model) {
		
		System.out.println("residents=" + residents);
		// Create an instance of BCryptPasswordEncoder
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

				// Encode the password
				String encodedPassword = encoder.encode(residents.getPassword());
				residents.setPassword(encodedPassword);
				residents.setRole("ROLE_RESIDENTS");

				RestTemplate restTemplate = new RestTemplate();

				// Set HTTP headers
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// Create HTTP request with JSON body
				HttpEntity<Residents> request = new HttpEntity<>(residents, headers);

				// Send POST request
				String url = "http://localhost:8090/api/v1/addResidents";
				Residents response = restTemplate.postForObject(url, request, Residents.class);
				
				 // Logging for debugging
		        System.out.println("Response from Server: " + response);
				
				ModelAndView view = new ModelAndView("/login");
				return view;
				
	}
	
	@GetMapping("/residenthome")
	public ModelAndView residentHome(Model model) {
		//Residents residents = new Residents();
		//model.addAttribute("residents",residents);
		Residents resident = (Residents) session.getAttribute("residents");
		
		System.out.println("residents=====" + resident);
		model.addAttribute("resident_name", resident.getFirstName()+" "+resident.getLastName());
		
		model.addAttribute("senderId", resident.getId());
		model.addAttribute("total_apartments", apartmentService.getTotalApartments());
		model.addAttribute("available_apartments", apartmentService.getAvailableApartments());
		model.addAttribute("maintainance_pending_count", maintenanceRequestService.countPendingRequests());
		ModelAndView view = new ModelAndView("residenthome");
		return view;
	} 

	
	
	
	
}
