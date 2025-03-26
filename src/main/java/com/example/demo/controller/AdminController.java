package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Residents;
import com.example.demo.repository.ResidentsRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
    private ResidentsRepository residentsRepository;
	
	 @GetMapping("/login")
	    public String showLoginForm() {
	        return "login"; // Returns the login HTML page
	    }

	    @PostMapping("/login")
	    public String login(@RequestParam("email") String email, 
	                        @RequestParam("password") String password, 
	                        Model model) {
	        
	        Optional<Residents> residentOptional = residentsRepository.findByUserNameAndPassword(email, password);
	        
	        if (!residentOptional.isPresent()) {
	            model.addAttribute("error", "Invalid credentials");
	            return "login"; // Reload login page with error message
	        }
	        
	        Residents resident = residentOptional.get();
	        model.addAttribute("resident", resident);
	        
	        return "redirect:/adminhome"; // Redirect to dashboard or another page upon successful login
	    }

	@PostMapping("/create")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@GetMapping("/all")
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public Optional<Admin> getAdminById(@PathVariable Long id) {
		return adminService.getAdminById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		return "Admin with ID " + id + " deleted successfully";
	}
}
