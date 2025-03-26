package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.demo.entity.Residents;

@Service
public interface ResidentsService {
	void addResidents(Residents residents);
	List<Residents> getAllResidents();
	boolean isResidentsExist(int id);
	Residents getResidentsById(int id);
	boolean deleteResidents(int id);
	boolean updateResidents(Residents residents);
<<<<<<< HEAD
=======
	Residents findResidentByUsername(String loggedInUserName);
	
	

>>>>>>> fffaefc7719c5f4e6311d299bf1a6f5517c1263b
}
