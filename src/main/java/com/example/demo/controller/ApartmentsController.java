package com.example.demo.controller;

import com.example.demo.entity.Apartments;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApartmentsRepository;
import com.example.demo.serviceimpl.ApartmentsServiceImpl;

import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
public class ApartmentsController {

    @Autowired
    private ApartmentsServiceImpl apartmentsService;
    
    
   



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

    // ========== REST APIs ==========

    @GetMapping("/apartments")
    public List<Apartments> getAllApartments() {
        return apartmentsService.getAllApartments();
    }

    @GetMapping("/apartments/{id}")
    public ResponseEntity<Apartments> getApartmentById(@PathVariable int id) {
        return apartmentsService.getApartmentsById(id)
                .map(apartment -> new ResponseEntity<>(apartment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/apartments")
    public ResponseEntity<String> addApartment(
            @RequestPart("apartment") Apartments apartment,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            apartmentsService.addApartments(apartment, imageFile);
            return new ResponseEntity<>("Apartment added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add apartment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/apartments/{id}")
    public ResponseEntity<String> updateApartment(
            @PathVariable int id,
            @RequestPart("apartment") Apartments apartment,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            apartment.setId(id);
            boolean updated = apartmentsService.updateApartmentsWithImage(apartment, imageFile);
            if (updated) {
                return new ResponseEntity<>("Apartment updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Apartment not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update apartment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/apartments/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable int id) {
        if (!apartmentsService.deleteApartments(id)) {
            throw new ResourceNotFoundException("Apartment", "Id", id);
        }
        return new ResponseEntity<>("Apartment deleted successfully", HttpStatus.OK);
    }

    // ========== Web MVC Views ==========

    @GetMapping("/addapartment")
    public String showAddApartmentForm(Model model) {
        model.addAttribute("apartments", new Apartments());
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

    @PostMapping("/addapartments")
    public String addApartmentWeb(@ModelAttribute("apartments") Apartments apartment,
                                  @RequestParam("imageFile") MultipartFile imageFile,
                                  Model model) {
        try {
            apartmentsService.addApartments(apartment, imageFile);
            model.addAttribute("successMessage", "Apartment added successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error adding apartment: " + e.getMessage());
        }
        return "addapartment";
    }

    @GetMapping("/viewapartments")
    public String viewApartments(Model model) {
        model.addAttribute("apartments", apartmentsService.getAllApartments());
        return "viewapartments";
    }

    @GetMapping("/editapartment/{id}")
    public String editApartment(@PathVariable int id, Model model) {
        Optional<Apartments> apartment = apartmentsService.getApartmentsById(id);
        if (apartment.isPresent()) {
            model.addAttribute("apartment", apartment.get());
            return "editapartment";
        } else {
            return "redirect:/viewapartments";
        }
    }


    @PostMapping("/updateapartment")
    public String updateApartmentWeb(@ModelAttribute Apartments apartment,
                                     @RequestParam("imageFile") MultipartFile imageFile,
                                     Model model) {
        try {
            apartmentsService.updateApartmentsWithImage(apartment, imageFile);
            model.addAttribute("successMessage", "Apartment updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating apartment: " + e.getMessage());
        }
        return "redirect:/viewapartments";
    }

    @GetMapping("/deleteapartment/{id}")
    public String deleteApartmentWeb(@PathVariable int id) {
        apartmentsService.deleteApartments(id);
        return "redirect:/viewapartments";
        
       

    }
}
