package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Facilities;
@Service
public interface FacilitiesService {
    Facilities createFacility(Facilities facility);
    Optional<Facilities> getFacilityById(int id);
    List<Facilities> getAllFacilities();
    List<Facilities> getActiveFacilities();
    Facilities updateFacility(int id, Facilities facility);
    void deleteFacility(int id);
}

