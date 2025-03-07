package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BookingRequests;


@Service
public interface  BookingRequestsService {
	void addbookings(BookingRequests bookings);
	List<BookingRequests> getAllBookings();
	BookingRequests getBookingsById(int bookingId);
	Boolean deleteBookings(int bookingId);
	Boolean updateBookings( int bookingId, BookingRequests bookings );
	
	
	

}
