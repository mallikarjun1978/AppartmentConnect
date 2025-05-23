package com.example.demo.service;

import  java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.MaintenanceRequest;

@Service
public interface MaintenanceRequestService {

	void addMaintenanceRequest(MaintenanceRequest maintenancerequest);

	List<MaintenanceRequest> getmaintenancerequest();

	boolean isMaintenanceRequestExist(int Id);

	MaintenanceRequest getMaintenanceRequestById(int Id);

	boolean deleteMaintenanceRequest(int Id);

	boolean updateMaintenanceRequest(MaintenanceRequest maintenanceRequest);

	
}
