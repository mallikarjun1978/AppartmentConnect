package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Apartments;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApartmentsRepository;
import com.example.demo.service.ApartmentsService;

@Service
public class ApartmentsServiceImpl implements ApartmentsService{

	@Autowired
	ApartmentsRepository apartmentsRepository;
	
	@Override
	public void addApartments(Apartments apartments) {
		// TODO Auto-generated method stub
		apartmentsRepository.save(apartments);
	}

	@Override
	public List<Apartments> getAllApartments() {
		// TODO Auto-generated method stub
		List<Apartments> apartmentsList=apartmentsRepository.findAll();
		return apartmentsList;
	}

	@Override
	public boolean isApartmentsExist(int Id) {
		// TODO Auto-generated method stub
		Optional<Apartments> apartments=apartmentsRepository.findById(Id);
		Apartments apart;
		if(apartments.isPresent()) {
			
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Apartments getApartmentsById(int Id) {
		// TODO Auto-generated method stub
		Optional<Apartments> apartments=apartmentsRepository.findById(Id);
		Apartments apart;
		if(apartments.isPresent()) {
			apart=apartments.get();
		}else {
			throw new ResourceNotFoundException("Apartments", "Id", Id);
		}
		return apart;
	}

	@Override
	public boolean deleteApartments(int Id) {
		// TODO Auto-generated method stub
		Optional<Apartments> apartments=apartmentsRepository.findById(Id);
		if(apartments.isPresent()) {
			apartmentsRepository.deleteById(Id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateApartments(Apartments apartments) {
		// TODO Auto-generated method stub
		Optional<Apartments> apartments1=apartmentsRepository.findById(apartments.getId());
		Apartments apart;
		if(apartments1.isPresent()) {
			apartmentsRepository.save(apartments);
			return true;
		}else {
		return false;
	}
	}

}
