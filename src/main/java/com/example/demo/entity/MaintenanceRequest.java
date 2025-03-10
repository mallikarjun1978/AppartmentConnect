package com.example.demo.entity;


import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "maintenance_requests")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "residentId")
    private int residentId; // Assuming you have a Resident entity

    @Column(name = "apartmentId")
    private int apartmentId; // Assuming you have an Apartment entity

    @Column(name = "requestDescription", nullable = true)
    private String requestDescription;

    
    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "createdAt")
    @CreatedDate
    private Timestamp createdAt;

  
}
   
    // Constructors, getters, and setters

    