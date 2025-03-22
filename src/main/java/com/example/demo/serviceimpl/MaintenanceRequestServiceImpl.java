package com.example.demo.serviceimpl;

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
	    public List<MaintenanceRequest> getRequestsByResidentId(int loggedInResidentId) {
	        // Find all maintenance requests associated with a specific resident
	        return maintenanceRepository.findByResidentId(loggedInResidentId);
	    }

	    @Override
	    public void saveRequest(MaintenanceRequest newRequest) {
	        // Save a new maintenance request to the repository
	        maintenanceRepository.save(newRequest);
	    }

		@Override
		public MaintenanceRequest findTopByOrderByCreatedAtDesc() {
			// TODO Auto-generated method stub
			return maintenanceRepository.findTopByOrderByCreatedAtDesc();
		}

		
		@Override
		 // Update the status of a maintenance request
	    public boolean updateStatus(Long requestId) {
	        MaintenanceRequest request = maintenanceRepository.findById(requestId).orElse(null);
	        if (request != null) {
	            // Toggle the status between 'Pending' and 'Completed'
	            if ("Pending".equals(request.getStatus())) {
	                request.setStatus("Completed");
	            } else {
	                request.setStatus("Pending");
	            }
	            maintenanceRepository.save(request);
	            return true;
	        }
	        return false;
	    }

		@Override
		public MaintenanceRequest findTopByResidentIdOrderByCreatedAtDesc(int residentId) {
		    return maintenanceRepository.findTopByResidentIdOrderByCreatedAtDesc(residentId);
		}

	

	
	
	}
	
	

	
		
