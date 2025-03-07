package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BookingRequests;
import com.example.demo.repository.BookingRequestsRepository;
import com.example.demo.service.BookingRequestsService;

@Service
public class BookingRequestsServiceimpl implements BookingRequestsService {
	@Autowired
	BookingRequestsRepository bookingsRepository;

	
	@Override
	public void addbookings(BookingRequests bookings) {
	 bookingsRepository.save(bookings);
		
	}
	

	@Override
	public List<BookingRequests> getAllBookings() {
		List<BookingRequests> bookinglist = bookingsRepository.findAll();
		return bookinglist;
	}

	@Override
	public BookingRequests getBookingsById(int bookingId) {

		Optional<BookingRequests> bookings = bookingsRepository.findById(bookingId);

		return bookings.orElse(null);
	}

	@Override
	public Boolean deleteBookings(int bookingId) {
		Optional<BookingRequests> bookings = bookingsRepository.findById(bookingId);
		if (bookings.isPresent()) {
			bookingsRepository.deleteById(bookingId);
			return true;
		} else {
			return false;
		}

	}

	


	@Override
	public Boolean updateBookings(int bookingId, BookingRequests bookings) {
		Optional<BookingRequests> existingBooking = bookingsRepository.findById(bookings.getBookingId());

		if (existingBooking.isPresent()) {
			BookingRequests updatedBooking = existingBooking.get();

			updatedBooking.setResidentId(bookings.getResidentId());
			updatedBooking.setApartmentId(bookings.getApartmentId());
			updatedBooking.setStatus(bookings.getStatus());
			updatedBooking.setCreatedDate(bookings.getCreatedDate());

			bookingsRepository.save(updatedBooking);

			return true;
		} else {
			return false;
		}
	}


	
	

}
