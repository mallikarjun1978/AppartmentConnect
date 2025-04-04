package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facilities;
import com.example.demo.repository.FacilitiesRepository;
import com.example.demo.service.FacilitiesService;

import java.util.List;
import java.util.Optional;

@Service
public class FacilitiesServiceImpl implements FacilitiesService {

   

    @Autowired
    FacilitiesRepository  facilitiesRepository;

    @Override
    public Facilities createFacility(Facilities facility) {
    	System.out.println("Saving facility: " + facility);
        return facilitiesRepository.save(facility);
    }

    @Override
    public Optional<Facilities> getFacilityById(int id) {
        return facilitiesRepository.findById(id);
    }

    @Override
    public List<Facilities> getAllFacilities() {
        return facilitiesRepository.findAll();
    }

    @Override
    public List<Facilities> getActiveFacilities() {
        return facilitiesRepository.findByIsActiveTrue();
    }

    @Override
    public Facilities updateFacility(int id, Facilities facility) {
        return facilitiesRepository.findById(id)
                .map(existingFacility -> {
                    existingFacility.setFacilityName(facility.getFacilityName());
                    existingFacility.setDescription(facility.getDescription());
                    existingFacility.setAvailableTime(facility.getAvailableTime());
                    existingFacility.setMaxCapacity(facility.getMaxCapacity());
                    existingFacility.setActive(facility.isActive());
                    return facilitiesRepository.save(existingFacility);
                })
                .orElseThrow(() -> new RuntimeException("Facility not found with ID: " + id));
    }

    @Override
    public void deleteFacility(int id) {
        if (facilitiesRepository.existsById(id)) {
            facilitiesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Facility not found with ID: " + id);
        }
    }
}

