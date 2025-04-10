package com.example.demo.serviceimpl;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceimpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Optional<Admin> getAdminById(Long id) {
		return adminRepository.findById(id);
	}

	

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}

	
}
