package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.example.demo.entity.BookingRequests;
import com.example.demo.service.BookingRequestsService;

@RestController
@RequestMapping("api/v1")
public class BookingRequestsController {
	@Autowired
	BookingRequestsService bookingservice;

	@PostMapping(value = "/addbookings")
	public ResponseEntity<BookingRequests> addbookings(@RequestBody BookingRequests bookings) {
		System.out.println("bookings=" + bookings);
		bookingservice.addbookings(bookings);
		return new ResponseEntity<BookingRequests>(bookings, HttpStatus.CREATED);
	}

	@GetMapping(value = "/allbookings")
	public ResponseEntity<List<BookingRequests>> getAllBookings() {
		List<BookingRequests> bookings = bookingservice.getAllBookings();
		ResponseEntity<List<BookingRequests>> entity = new ResponseEntity<>(bookings, HttpStatus.OK);
		return entity;
	}

	@GetMapping(value = "/getbookings/{bookingId}")
	public ResponseEntity<BookingRequests> getbookings(@PathVariable("bookingId") int bookingId) {
		BookingRequests bookings = bookingservice.getBookingsById(bookingId);

		if (bookings != null) {
			return ResponseEntity.ok(bookings);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/deletebookings/{bookingId}")
	public ResponseEntity<Boolean> deleteBookings(@PathVariable("bookingId") int bookingId) {
		boolean flag = bookingservice.deleteBookings(bookingId);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}

	
	  @PutMapping(value = "/updatebookings/{bookingId}") public
	  ResponseEntity<Boolean> updateBookings(@PathVariable("bookingId") int
	  bookingId, @RequestBody BookingRequests updatedBooking) { boolean flag =
	  bookingservice.updateBookings(bookingId, updatedBooking); return new
	  ResponseEntity<>(flag, HttpStatus.OK); }
	 

}
