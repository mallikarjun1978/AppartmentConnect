package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Residents;

@Service
public interface ResidentsRepository extends JpaRepository<Residents,Integer>{

}
