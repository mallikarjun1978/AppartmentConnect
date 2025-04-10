package com.example.demo.serviceimpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Residents;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ResidentsRepository;
import com.example.demo.service.ResidentsService;

@Service
public class ResidentsServiceImpl implements ResidentsService {
	@Autowired
	ResidentsRepository residentsRepository;
	
	@Override
	public void addResidents(Residents residents) {
		//residents.setRole("ROLE_RESIDENT");
		residentsRepository.save(residents);
	}

	@Override
	public List<Residents> getAllResidents() {
		List<Residents> residentsList = residentsRepository.findAll();
		return residentsList;
	}

	@Override
	public boolean isResidentsExist(int id) {
		Optional<Residents> resident = residentsRepository.findById(id);
		Residents residents;
		if(resident.isPresent()) {
			
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Residents getResidentsById(int id) {

		Optional<Residents> resident = residentsRepository.findById(id);
		Residents residents;
		if(resident.isPresent()) {
			residents = resident.get();
			
		}else {
			throw new ResourceNotFoundException("Residents", "id", id);
		}
		return residents;
	}

	@Override
	public boolean deleteResidents(int id) {

		Optional<Residents> resident = residentsRepository.findById(id);
		Residents residents;
		if(resident.isPresent()) {
			residentsRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean updateResidents(Residents residents) {
	    Optional<Residents> resident1 = residentsRepository.findById(residents.getId());
	    if (resident1.isPresent()) {
	        residentsRepository.save(residents);
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	 public Residents findResidentByUsername(String loggedInUserName) {
        // Find and return the Resident entity by username
        return residentsRepository.findByUserName(loggedInUserName);
    }

	
	

	

	


}