package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BookingRequests;

public interface BookingRequestsRepository extends JpaRepository<BookingRequests,Integer>{

	Optional<BookingRequests> findById(int bookingId);

    public Boolean deleteById(int bookingId);

	List<BookingRequests> findAllByOrderByCreatedDateDesc(PageRequest of);

	BookingRequests findTopByResidentIdOrderByCreatedDateDesc(int residentId);
	List<BookingRequests> getAllBookingsByResidentId(int residentId);




	

	

}
