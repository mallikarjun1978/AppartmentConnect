package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MaintenanceRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.MaintenanceRequestService;

@RestController
 @RequestMapping("/api/v1")
public class MaintenanceRequestController {
@Autowired
private MaintenanceRequestService    maintenancerequestservice;

    @PostMapping(value="/addMaintenanceRequest")
    public ResponseEntity<String> addMaintenanceRequest(@RequestBody MaintenanceRequest maintenancerequest){
    	maintenancerequestservice.addMaintenanceRequest(maintenancerequest);
		return new ResponseEntity<>("Request Added", HttpStatus.CREATED);
		
	
	}
    
	
	
    @GetMapping(value = "/allMaintenanceRequest")
	public List<MaintenanceRequest> getAllMaintenanceRequest(){
		return maintenancerequestservice.getmaintenancerequest();
	
    }
    
    
    @GetMapping("/MaintenanceRequest/{Id}")
    public ResponseEntity<MaintenanceRequest> getMaintenanceRequestById(@PathVariable int Id){
    	MaintenanceRequest maintenanceRequest=maintenancerequestservice.getMaintenanceRequestById(Id);
    	return new ResponseEntity<>(maintenanceRequest,HttpStatus.OK);
    }
    
    

    
@DeleteMapping("/MaintenanceRequest/{Id}")
public ResponseEntity<String> deleteMaintenanceRequestService(@PathVariable int Id){
	if(!maintenancerequestservice.deleteMaintenanceRequest(Id)) {
		throw new ResourceNotFoundException("MaintenanceRequest", "Id",Id);
	}
	return new ResponseEntity<>("Maintenance request Added",HttpStatus.OK);
}

@PutMapping("/MaintenanceRequest/{Id}")
public ResponseEntity<Boolean> updateMaintenance(@PathVariable("Id") int Id, @RequestBody MaintenanceRequest maintenanceRequest){
	boolean flag;
	if(maintenancerequestservice.isMaintenanceRequestExist(Id)) {
		flag = maintenancerequestservice.updateMaintenanceRequest(maintenanceRequest);
		
	}else {
		flag = false;
	}
	return new ResponseEntity<>(flag, HttpStatus.OK);
}




}



    
    
   