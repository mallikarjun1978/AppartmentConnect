package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Apartments;
@Service
public interface ApartmentsService {
	List<Apartments> getAllApartments();
	boolean isApartmentsExist(int Id);
	Optional<Apartments> getApartmentsById(int Id);
	boolean deleteApartments(int Id);
	boolean updateApartments(Apartments apartments);
	long getTotalApartments();
	long getAvailableApartments();
	void addApartments(Apartments apartment, MultipartFile imageFile) throws IOException;
	boolean updateApartmentsWithImage(Apartments updatedApartment, MultipartFile imageFile) throws IOException;
	
}
