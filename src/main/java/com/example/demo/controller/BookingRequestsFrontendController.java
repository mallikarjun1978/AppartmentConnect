package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.BookingRequests;
import com.example.demo.service.BookingRequestsService;

@Controller
@RequestMapping("/admin/bookings")
public class BookingRequestsFrontendController {

    @Autowired
    private BookingRequestsService bookingRequestService;

    // GET all bookings
    @GetMapping
    public String viewAllBookings(Model model) {
        List<BookingRequests> bookings = bookingRequestService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings"; // Make sure you have bookings.html under templates
    }

    // POST to update booking status (ACCEPTED / REJECTED)
    @PostMapping("/{id}/status")
    public String updateBookingStatus(@PathVariable int id, @RequestParam("status") String status) {
        BookingRequests booking = bookingRequestService.getBookingsById(id);
        if (booking != null) {
            booking.setStatus(status);
            bookingRequestService.addbookings(booking); // saves updated status
        }
        return "redirect:/admin/bookings"; // redirect back to the admin booking list
    }
}
