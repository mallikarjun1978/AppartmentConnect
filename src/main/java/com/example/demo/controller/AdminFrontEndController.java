package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.MaintenanceRequest;
<<<<<<< HEAD
import com.example.demo.entity.Messages;
import com.example.demo.entity.Residents;
import com.example.demo.service.ApartmentsService;
import com.example.demo.service.MaintenanceRequestService;
import com.example.demo.service.MessagesService;
=======
import com.example.demo.entity.Residents;
import com.example.demo.service.MaintenanceRequestService;
>>>>>>> fffaefc7719c5f4e6311d299bf1a6f5517c1263b

@Controller
@RequestMapping("/front-end/admin")
public class AdminFrontEndController {
<<<<<<< HEAD

	@Autowired
    private MessagesService messagesService;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;
    
    @Autowired
    private ApartmentsService apartmentsService;

    @GetMapping("/adminhome")
    public ModelAndView adminHome() {
        // Get the last 3 unread messages (you may want to pass the actual receiverId)
        List<Messages> lastThreeMessages = messagesService.getLastThreeUnreadMessages(1L); // Example receiverId (admin's ID)

        // Get pending maintenance requests
        List<MaintenanceRequest> pendingMaintenanceRequests = maintenanceRequestService.getPendingMaintenanceRequests();

        // Create ModelAndView object
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminhome");

        // Add data to the model
        modelAndView.addObject("lastThreeMessages", lastThreeMessages);
        modelAndView.addObject("pendingMaintenanceRequests", pendingMaintenanceRequests);
        
     // Fetch the data for the dashboard
        long totalApartments = apartmentsService.getTotalApartments();
        long availableApartments = apartmentsService.getAvailableApartments();

        // Add data to the model
        modelAndView.addObject("totalApartments", totalApartments);
        modelAndView.addObject("availableApartments", availableApartments);


        return modelAndView;
}}
=======
	
	 
	 @GetMapping("/adminhome")
	    public ModelAndView login(Model model) {
	    	ModelAndView view = new ModelAndView("adminhome");
			return view;
	    }
}
>>>>>>> fffaefc7719c5f4e6311d299bf1a6f5517c1263b
