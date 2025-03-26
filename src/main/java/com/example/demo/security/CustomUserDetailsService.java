package com.example.demo.security;

import java.net.Authenticator.RequestorType;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Residents;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.ResidentsRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	ResidentsRepository residentRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	private HttpSession session; // Inject HttpSession

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("IM HERE...");
		Collection<? extends GrantedAuthority> authorities = null;

		Residents residents = residentRepository.findByUserName(username);
		if (residents != null) {
			///////////
			System.out.println("residents=" + residents);
			// Store the resident in session
			session.setAttribute("residents", residents);

			// Encode the password

			if (residents.getRole().equals("ROLE_RESIDENTS")) {
				authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_RESIDENTS"));
				return new org.springframework.security.core.userdetails.User(username, residents.getPassword(),
						authorities);
			}
		}
		//////////////
		///
		///

		Admin admin = adminRepository.findByEmail(username);
		if (admin != null) {
			///////////
			System.out.println("admin=" + admin);
			// Store the admin in session
			session.setAttribute("admin", admin);

			authorities = null;

			if (admin.getRole().equals("ROLE_ADMIN")) {
				authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
				return new org.springframework.security.core.userdetails.User(username, admin.getPassword(),
						authorities);
			}
		}
		return null;
	}

}
