package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Facilities;
import com.example.demo.service.FacilitiesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facilities")
public class FacilitiesController {

    

    @Autowired
    FacilitiesService facilitiesService;

    @PostMapping
    public Facilities createFacility(@RequestBody Facilities facility) {
        return facilitiesService.createFacility(facility);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facilities> getFacilityById(@PathVariable int id) {
        return facilitiesService.getFacilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Facilities> getAllFacilities() {
        return facilitiesService.getAllFacilities();
    }

    @GetMapping("/active")
    public List<Facilities> getActiveFacilities() {
        return facilitiesService.getActiveFacilities();
    }

    @PutMapping("/{id}")
    public Facilities updateFacility(@PathVariable int id, @RequestBody Facilities facility) {
        return facilitiesService.updateFacility(id, facility);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable int id) {
        facilitiesService.deleteFacility(id);
        return ResponseEntity.noContent().build();
    }
}

