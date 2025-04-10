package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Facilities;
import com.example.demo.service.FacilitiesService;

@Controller
public class FacilitiesFrontEndController {
	
	@Autowired
    private FacilitiesService facilitiesService;

    // Mapping for Add Facilities Page
    @GetMapping("/add_facilities")
    public ModelAndView showAddFacilitiesPage() {
        ModelAndView modelAndView = new ModelAndView("add_facilities"); // Name of the HTML file (add_facilities.html)
        modelAndView.addObject("pageTitle", "Add New Facility"); // Passing data to the view
        return modelAndView;
    }
    
 // Handling POST request to add a facility
    @PostMapping("/add-facilities")
    @ResponseBody
    public String addFacility(@RequestBody Facilities facility) {
        System.out.println("Received Facility: " + facility);
        try {
            facilitiesService.createFacility(facility);
            System.out.println("Facility saved successfully!");
            return "Facility added successfully!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Failed to add facility. Please try again.";
        }
    }

    // Mapping for View Facilities Page
    @GetMapping("/view_facilities")
    public ModelAndView showViewFacilitiesPage() {
        ModelAndView modelAndView = new ModelAndView("view_facilities");
        List<Facilities> facilitiesList = facilitiesService.getAllFacilities();
       // System.out.println("Facilities List: " + facilitiesList); // Debugging
        modelAndView.addObject("facilities", facilitiesList);
        return modelAndView;
    }
    
    // Edit facility
    @PostMapping("/edit/{id}")
    @ResponseBody
    public String updateFacility(@PathVariable int id, @RequestBody Facilities facility) {
        System.out.println("Updated facility: " + facility);
    	try {
            facilitiesService.updateFacility(id, facility);
            return "Facility updated successfully!"; // ✅ Success message
        } catch (Exception e) {
            System.out.println("Error updating facility: " + e.getMessage());
            return "Failed to update facility."; // ❌ Error message
        }
    }
    // Delete facility
    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteFacility(@PathVariable int id) {
        try {
            facilitiesService.deleteFacility(id);
            return "Facility deleted successfully!";  // ✅ Success message
        } catch (Exception e) {
            System.out.println("Error deleting facility: " + e.getMessage());
            return "Failed to delete facility.";  // ❌ Error message
        }
    }


}
