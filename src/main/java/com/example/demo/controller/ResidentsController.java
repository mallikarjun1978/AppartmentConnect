package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Residents;
import com.example.demo.service.ResidentsService;

@RestController
@RequestMapping("api/v1")
public class ResidentsController {
	@Autowired
	ResidentsService residentsService;
	
	@PostMapping(value="/addResidents")
	public ResponseEntity<Residents> addResidents(@RequestBody Residents residents){
		residentsService.addResidents(residents);
		return new ResponseEntity<Residents>(residents,HttpStatus.CREATED);
	}
	
	@GetMapping("/auth/residentslogin")
	public ModelAndView loginmethod(Model model) {
		return new ModelAndView("login");
	}

	
	
	@GetMapping(value = "/allResidents")
	public ResponseEntity<List<Residents>> getAllResidents() {
		List<Residents> residents1 = residentsService.getAllResidents();
		ResponseEntity<List<Residents>> entity = new ResponseEntity<>(residents1, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping(value = "/getResidents/{id}")
	public ResponseEntity<Residents> getResidents(@PathVariable("id") int id) {
		Residents residents = residentsService.getResidentsById(id);

		if (residents != null) {
			return ResponseEntity.ok(residents);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping(value = "/getResidents1")
	public ResponseEntity<Object> getResidents1(@PathVariable("id") int id) {
		Residents residents;

		if (residentsService.isResidentsExist(id)) {
			residents = residentsService.getResidentsById(id);

		} else {
			residents = null;
		}
		ResponseEntity<Object> entity = new ResponseEntity<>(residents, HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping(value = "/deleteresidents/{id}")
	public ResponseEntity<Boolean> deleteResidents(@PathVariable("id") int id) {
		System.out.println("id:" + id);
		boolean flag;
		if (residentsService.isResidentsExist(id)) {
			flag = residentsService.deleteResidents(id);

		} else {
			flag = false;
		}
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	@PutMapping("/updateResidents/{id}")
	public ResponseEntity<Boolean> updateResidents(@PathVariable("id") int id,
			@RequestBody Residents residents) {
		boolean flag;
		if (residentsService.isResidentsExist(id)) {
			flag = residentsService.updateResidents(residents);

		} else {
			flag = false;
		}
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	
		
}
