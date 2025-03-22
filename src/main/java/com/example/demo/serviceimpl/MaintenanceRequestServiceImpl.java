package com.example.demo.serviceimpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MaintenanceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MaintenanceRequestRepository;

import com.example.demo.service.MaintenanceRequestService;

@Service
public  class MaintenanceRequestServiceImpl implements MaintenanceRequestService {
	private static final Integer MaintenanceRequestRepository = null;
	@Autowired
	MaintenanceRequestRepository maintenanceRepository;

	@Override
	public void addMaintenanceRequest(MaintenanceRequest maintenancerequest) {
		// TODO Auto-generated method stub
		maintenanceRepository.save(maintenancerequest);
	}

	@Override
	public List<MaintenanceRequest> getmaintenancerequest() {
		List<MaintenanceRequest> MaintenanceRequestList = maintenanceRepository.findAll();
		return MaintenanceRequestList;
	}

	@Override
	public boolean isMaintenanceRequestExist(int Id) {
		Optional<MaintenanceRequest> Maintenance =maintenanceRepository.findById(Id);
		return false;
	}

	@Override
	public MaintenanceRequest getMaintenanceRequestById(int Id) {
		Optional<MaintenanceRequest> Maintenance = maintenanceRepository.findById(Id);
		
		MaintenanceRequest  maintenance;
		if( Maintenance.isPresent()) {
			maintenance = Maintenance.get();
;
			}else {
				throw new ResourceNotFoundException("Maintenance","Id",Id);
				
			}
		return maintenance;
	}
		
		



	@Override
	
	public boolean deleteMaintenanceRequest(int Id) {
		
		Optional<MaintenanceRequest> maintenance= maintenanceRepository.findById(Id);
		if(maintenance.isPresent()) {
			maintenanceRepository.deleteById(Id);
			return true;
			
		} else {
			return false;
		}
		}
				
	

	@Override
	public boolean updateMaintenanceRequest(MaintenanceRequest maintenancerequest) {
		Optional<MaintenanceRequest> Maintenance1 = maintenanceRepository.findById(maintenancerequest.getId());
		MaintenanceRequest  maintance;
		if(Maintenance1.isPresent()) {
			maintenanceRepository.save(maintenancerequest);
			return true;
		}else {
		
		return false;
	}
	}

	@Override
	public List<MaintenanceRequest> getPendingRequests() {
        return maintenanceRepository.findByStatus("Pending");
    }

    public long countPendingRequests() {
        return maintenanceRepository.countByStatus("Pending");
    }

	

	
	
	}
	
	

	
		
