package com.example.demo.controller;

import com.example.demo.entity.Apartments;
import com.example.demo.service.ApartmentsService;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Enable CORS if needed for front-end interactions
@RequestMapping("/api/v1")
public class ApartmentsController {
    
    @Autowired
    private ApartmentsService apartmentsService;

    // Get all apartments
    @GetMapping("/apartments")
    public List<Apartments> getAllApartments() {
        return apartmentsService.getAllApartments();
    }

    // Get apartment by id
    @GetMapping("/apartments/{id}")
    public ResponseEntity<Apartments> getApartmentById(@PathVariable int id) {
        Apartments apartment = apartmentsService.getApartmentsById(id);
        return new ResponseEntity<>(apartment, HttpStatus.OK);
    }

    // Add a new apartment
    @PostMapping("/apartments")
    public ResponseEntity<String> addApartment(@RequestBody Apartments apartments) {
        apartmentsService.addApartments(apartments);
        return new ResponseEntity<>("Apartment added successfully", HttpStatus.CREATED);
    }

    // Update an existing apartment
    @PutMapping("/apartments/{id}")
	public ResponseEntity<Boolean> updateApartments(@PathVariable("id")int id, @RequestBody Apartments apartments){
		boolean flag;
		if(apartmentsService.isApartmentsExist(id)) {
			flag=apartmentsService.updateApartments(apartments);
		}else {
			flag=false;
		}
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
	

    // Delete an apartment
    @DeleteMapping("/apartments/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable int id) {
        if (!apartmentsService.deleteApartments(id)) {
            throw new ResourceNotFoundException("Apartment", "Id", id);
        }
        return new ResponseEntity<>("Apartment deleted successfully", HttpStatus.OK);
    }
}
