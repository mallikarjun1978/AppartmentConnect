package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Residents;

@Service
public interface ResidentsRepository extends JpaRepository<Residents,Integer>{
	Optional<Residents> findByUserNameAndPassword(String userName, String password);
	Residents findByUserName(String userName);

   
}