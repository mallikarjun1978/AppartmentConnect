package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Apartments;

public interface ApartmentsService {
	void addApartments(Apartments apartments);
	List<Apartments> getAllApartments();
	boolean isApartmentsExist(int Id);
	Apartments getApartmentsById(int Id);
	boolean deleteApartments(int Id);
	boolean updateApartments(Apartments apartments);
	

}
