package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="residents")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // Ensure auditing is enabled
@ToString
public class Residents {
	@Id
	@Column(name="id",nullable=false)
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="user_name",nullable=true)
	private String userName;
	@Column(name="password",nullable=true)
	private String password;
	@Column(name="first_name",nullable=true)
	private String firstName;
	@Column(name="last_name",nullable=true)
	private String lastName;
	@Column(name="email",nullable=true)
	private String email;
	@Column(name="phone",nullable=true)
	private String phone;
	@Column(name="address",nullable=true)
	private String address;
	@Column(name="registration_date",nullable=true)
	@CreatedDate
	private Timestamp registrationDate;
	
	@Column
	private String role;
	
	
	
	
}
