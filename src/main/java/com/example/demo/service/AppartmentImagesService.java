package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AppartmentImages;

@Service
public interface AppartmentImagesService {
	void addAppartmentImage(AppartmentImages appartmentimage);
	List<AppartmentImages> getAllAppartmentImages();
	boolean isApartmentImagesExist(int appartmentId);
	AppartmentImages getApartmentImagesById(int appartmentId);
	boolean deleteApartmentImages(int appartmentId);
	boolean updateAppartmentImages(AppartmentImages appartmentimage );

}
