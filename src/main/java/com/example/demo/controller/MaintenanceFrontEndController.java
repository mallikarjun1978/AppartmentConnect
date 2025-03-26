package com.example.demo.controller;



import com.example.demo.entity.MaintenanceRequest;
import com.example.demo.entity.Residents;
import com.example.demo.service.MaintenanceRequestService;
import com.example.demo.service.ResidentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MaintenanceFrontEndController {

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private ResidentsService residentService; // Inject ResidentService

    @GetMapping("/maintenance_requests")
    public String showRequests(Model model) {
        // Get the current logged-in user from Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName(); // This gets the username of the logged-in user

        // Fetch the Resident entity based on the username
        Residents loggedInResident = residentService.findResidentByUsername(loggedInUserName);

        // Get the residentId from the Resident entity
        int loggedInResidentId = loggedInResident.getId();

        // Fetch maintenance requests for the logged-in resident
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestService.getRequestsByResidentId(loggedInResidentId);

        model.addAttribute("maintenanceRequests", maintenanceRequests);
        return "maintenance_request";
    }

    @PostMapping("/maintenance_request")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> addRequest(@RequestParam String requestDescription) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserName = authentication.getName(); // Get the username of the logged-in user

        // Fetch the Resident entity based on the username
        Residents loggedInResident = residentService.findResidentByUsername(loggedInUserName);

        // Get the residentId from the Resident entity
        int loggedInResidentId = loggedInResident.getId();

        MaintenanceRequest newRequest = new MaintenanceRequest();
        newRequest.setResidentId(loggedInResidentId);
        newRequest.setRequestDescription(requestDescription);
        newRequest.setStatus("Pending");

        try {
            maintenanceRequestService.saveRequest(newRequest);
            // Return success as JSON response
            Map<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Return failure status in case of an error
            Map<String, Boolean> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    
    // Get the most recent maintenance request
    @GetMapping("/latest_request")
    public ResponseEntity<?> getLatestRequest() {
        try {
            // Get the current logged-in user from Spring Security
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String loggedInUserName = authentication.getName(); // This gets the username of the logged-in user

            // Fetch the Resident entity based on the username
            Residents loggedInResident = residentService.findResidentByUsername(loggedInUserName);

            // Get the residentId from the Resident entity
            int loggedInResidentId = loggedInResident.getId();

            // Fetch the latest maintenance request for the logged-in resident
            MaintenanceRequest latestRequest = maintenanceRequestService.findTopByResidentIdOrderByCreatedAtDesc(loggedInResidentId);

            if (latestRequest != null) {
                return ResponseEntity.ok(latestRequest);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No requests found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching latest request.");
        }
    }
    
    
    
    

    // Display the maintenance requests
    @GetMapping("/maintenance-requests")
    public String getMaintenanceRequests(Model model) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestService.getmaintenancerequest();
        //System.out.println("Maintenance Requests: " + maintenanceRequests); // Debugging line
        model.addAttribute("maintenanceRequests", maintenanceRequests);
        return "admin_maintenance"; // This is the Thymeleaf template name
    }

    // Handle the status update
    @PostMapping("/update-maintenance-status")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> updateMaintenanceStatus(@RequestParam Long Id) {
        boolean success = maintenanceRequestService.updateStatus(Id);
        
        // Create a map to hold the response
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        
        // Return the response as JSON
        if (success) {
            return ResponseEntity.ok(response);  // HTTP 200 for success
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // HTTP 500 for failure
        }
    }

}

