package com.example.demo.service;

import com.example.demo.entity.Admin;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public interface AdminService {
	Admin saveAdmin(Admin admin);

	List<Admin> getAllAdmins();

	Optional<Admin> getAdminById(Long id);

	
	void deleteAdmin(Long id);
}
