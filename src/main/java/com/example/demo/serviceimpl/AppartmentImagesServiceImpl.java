package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppartmentImages;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppartmentImagesRepository;
import com.example.demo.service.AppartmentImagesService;

@Service
public class AppartmentImagesServiceImpl implements AppartmentImagesService {
	
	@Autowired
	AppartmentImagesRepository appartmentImagesRepository;

	@Override
	public void addAppartmentImage(AppartmentImages appartmentimages) {
		appartmentImagesRepository.save(appartmentimages);
		
	}

	@Override
	public List<AppartmentImages> getAllAppartmentImages() {
		List<AppartmentImages> appartmentImagesList = appartmentImagesRepository.findAll();
		return appartmentImagesList;
	}

	@Override
	public boolean isApartmentImagesExist(int appartmentId) {
		Optional<AppartmentImages> appartmentImages = appartmentImagesRepository.findById(appartmentId);
		AppartmentImages appartmentImages1;
		if(appartmentImages.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AppartmentImages getApartmentImagesById(int appartmentId) {
		
		Optional<AppartmentImages> appartmentImages = appartmentImagesRepository.findById(appartmentId);
		AppartmentImages appartmentImages1;
		if(appartmentImages.isPresent()) {
			appartmentImages1  = appartmentImages.get();
		} else {
			throw new ResourceNotFoundException("AppartmentImages","appartmentId ",appartmentId );
		}
		return appartmentImages1;
	}

	@Override
	public boolean deleteApartmentImages(int appartmentId) {
		Optional<AppartmentImages> appartmentImages = appartmentImagesRepository.findById(appartmentId);
		if(appartmentImages.isPresent()) {
			appartmentImagesRepository.deleteById(appartmentId);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateAppartmentImages(AppartmentImages appartmentimage) {
		// TODO Auto-generated method stub
		Optional<AppartmentImages> appartmentImages1 = appartmentImagesRepository.findById(appartmentimage.getAppartmentId());
		
		if(appartmentImages1.isPresent()) {
			appartmentImagesRepository.save(appartmentimage);	
			return true;
		}else {
			return false;
		}
	}

}
