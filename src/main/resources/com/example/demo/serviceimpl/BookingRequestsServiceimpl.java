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
    private BookingRequestsRepository bookingRequestsRepository;

    @Override
    public void addbookings(BookingRequests bookingRequest) {
        System.out.println("Saving booking request: " + bookingRequest);
        bookingRequestsRepository.save(bookingRequest);
    }

    @Override
    public List<BookingRequests> getAllBookings() {
        return bookingRequestsRepository.findAll();
    }

    @Override
    public BookingRequests getBookingsById(int bookingId) {
        Optional<BookingRequests> booking = bookingRequestsRepository.findById(bookingId);
        return booking.orElse(null); // Return null if booking is not found
    }

    @Override
    public Boolean deleteBookings(int bookingId) {
        if (bookingRequestsRepository.existsById(bookingId)) {
            bookingRequestsRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateBookings(int bookingId, BookingRequests bookings) {
        if (bookingRequestsRepository.existsById(bookingId)) {
            bookings.setBookingId(bookingId); // Ensure the ID remains the same
            bookingRequestsRepository.save(bookings);
            return true;
        }
        return false;
    }
}

