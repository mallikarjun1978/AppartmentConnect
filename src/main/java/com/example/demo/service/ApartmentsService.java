package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Apartments;
@Service
public interface ApartmentsService {
	void addApartments(Apartments apartments);
	List<Apartments> getAllApartments();
	boolean isApartmentsExist(int Id);
	Optional<Apartments> getApartmentsById(int Id);
	boolean deleteApartments(int Id);
	boolean updateApartments(Apartments apartments);
	

}
