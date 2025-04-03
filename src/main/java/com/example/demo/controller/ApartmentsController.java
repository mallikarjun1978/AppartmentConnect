package com.example.demo.controller;

import com.example.demo.entity.Apartments;
import com.example.demo.service.ApartmentsService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
public class ApartmentsController {

	@Autowired
	private ApartmentsService apartmentsService;

	@GetMapping("/apartments")
	public List<Apartments> getAllApartments() {
		return apartmentsService.getAllApartments();
	}

	@GetMapping("/apartments/{id}")
	public ResponseEntity<Apartments> getApartmentById(@PathVariable int id) {
		Optional<Apartments> apartmentOptional = apartmentsService.getApartmentsById(id);

		if (apartmentOptional.isPresent()) {
			return new ResponseEntity<>(apartmentOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/apartments")
	public ResponseEntity<String> addApartment(@RequestBody Apartments apartments) {
		apartmentsService.addApartments(apartments);
		return new ResponseEntity<>("Apartment added successfully", HttpStatus.CREATED);
	}

	@PutMapping("/apartments/{id}")
	public ResponseEntity<Boolean> updateApartments(@PathVariable("id") int id, @RequestBody Apartments apartments) {
		boolean flag;
		if (apartmentsService.isApartmentsExist(id)) {
			flag = apartmentsService.updateApartments(apartments);
		} else {
			flag = false;
		}
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}

	@DeleteMapping("/apartments/{id}")
	public ResponseEntity<String> deleteApartment(@PathVariable int id) {
		if (!apartmentsService.deleteApartments(id)) {
			throw new ResourceNotFoundException("Apartment", "Id", id);
		}
		return new ResponseEntity<>("Apartment deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/addapartment")
	public ModelAndView addProduct(Model model) {
		Apartments apartments = new Apartments();
		model.addAttribute("apartments", apartments);
		ModelAndView view = new ModelAndView("addapartment");
		return view;
	}

	@PostMapping("/addapartments")
	public String addApartment(@ModelAttribute Apartments apartment, Model model) {
		try {
			apartmentsService.addApartments(apartment);
			model.addAttribute("successMessage", "Apartment added successfully!");
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Failed to add apartment. Please try again.");
		}
		return "addapartment";
	}

	@GetMapping("/viewapartments")
	public ModelAndView viewApartments(Model model) {
		System.out.println("ok1111");
		List<Apartments> apartmentsList = apartmentsService.getAllApartments();
		for (Apartments app : apartmentsList) {
			System.out.println(app);
		}
		model.addAttribute("apartments", apartmentsList);
		ModelAndView view = new ModelAndView("viewapartments");
		return view;
	}

	@GetMapping("/editapartment/{id}")
	public ModelAndView editApartment(@PathVariable int id, Model model) {
	    // Retrieve the apartment by id
	    Optional<Apartments> apartment = apartmentsService.getApartmentsById(id);

	    if (apartment.isPresent()) {
	        // If the apartment exists, add it to the model
	        model.addAttribute("apartment", apartment.get());  // Use "apartment" instead of "apartments" to match the model name

	        // Return a ModelAndView with the view name and model
	        return new ModelAndView("editapartment"); // The view name (editapartment.jsp or editapartment.html)
	    } else {
	        // If the apartment doesn't exist, redirect to the list of apartments
	        return new ModelAndView("redirect:/viewapartments");
	    }
	}


	@PostMapping("/updateapartment")
	public ModelAndView updateApartment(@ModelAttribute Apartments apartment) {
		ModelAndView modelAndView = new ModelAndView("redirect:/viewapartments");
		Optional<Apartments> existingApartmentOptional = apartmentsService.getApartmentsById(apartment.getId());

		if (existingApartmentOptional.isPresent()) {
			Apartments existingApartment = existingApartmentOptional.get();

			existingApartment.setName(apartment.getName());
			existingApartment.setLocation(apartment.getLocation());
			existingApartment.setPrice(apartment.getPrice());
			existingApartment.setIsAvailable(apartment.getIsAvailable());
			existingApartment.setDescription(apartment.getDescription());
			existingApartment.setCreatedAt(apartment.getCreatedAt());
			apartmentsService.updateApartments(existingApartment);
		} else {
			System.out.println("Apartment with ID " + apartment.getId() + " not found.");
		}

		return modelAndView;
	}

	@GetMapping("/deleteapartment/{id}")
	public ModelAndView deleteApartments(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/viewapartments");

		apartmentsService.deleteApartments(id);

		return modelAndView;
	}

}
