package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MaintenanceRequest;
import com.example.demo.entity.Messages;
import com.example.demo.entity.Residents;
import com.example.demo.entity.Apartments;
import com.example.demo.entity.BookingRequests;
import com.example.demo.service.MaintenanceRequestService;
import com.example.demo.service.MessagesService;
import com.example.demo.service.ResidentsService;
import com.example.demo.service.ApartmentsService;
import com.example.demo.service.BookingRequestsService;


@Controller
@RequestMapping("/admin")
public class AdminDashBoard {

    @Autowired
    private MessagesService messageService;

    @Autowired
    private MaintenanceRequestService maintenanceRequestService;

    @Autowired
    private BookingRequestsService bookingRequestService;

    @Autowired
    private ResidentsService residentService;

    @Autowired
    private ApartmentsService apartmentService;

    @GetMapping("/home")
    public String getAdminDashboard(Model model) {
        Long receiverId = 1L; // Replace with actual receiverId, like the logged-in admin's ID

        // Fetch the last 3 unread messages
        List<Messages> lastThreeMessages = messageService.getLastThreeUnreadMessages(receiverId);

        // Fetch the last 3 pending maintenance requests
        List<MaintenanceRequest> lastThreePendingRequests = maintenanceRequestService.getPendingRequests().stream()
                .limit(3)
                .toList();

        // Fetch the booking data (resident and apartment details)
        List<BookingRequests> bookings = bookingRequestService.getAllBookings();
        List<Map<String, Object>> bookingDetails = new ArrayList<>();

        for (BookingRequests booking : bookings) {
            // Fetch Resident and Apartment details based on the IDs
            Residents resident = residentService.getResidentsById(booking.getResidentId());
            Apartments apartment = apartmentService.getApartmentsById(booking.getApartmentId());

            // Prepare a map to pass the booking info, resident info, and apartment info
            Map<String, Object> bookingInfo = new HashMap<>();
            bookingInfo.put("residentName", resident.getUserName());
            bookingInfo.put("apartmentName", apartment.getName());
            bookingInfo.put("booking", booking);

            // Add to the list
            bookingDetails.add(bookingInfo);
        }

        // Add data to the model
        model.addAttribute("lastThreeMessages", lastThreeMessages);
        model.addAttribute("lastThreePendingRequests", lastThreePendingRequests);
        model.addAttribute("bookingDetails", bookingDetails);

        return "adminhome"; // Your Thymeleaf template name
    }
}
