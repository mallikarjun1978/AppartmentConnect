package com.example.demo.entity;

import lombok.*;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable =  true)
	private String username;

	@Column(nullable =  true)
	private String password;

	@Column(nullable =  true)
	private String firstName;

	@Column(nullable =  true)
	private String lastName;

	@Column(nullable =  true)
	private String email;

	private String phone;

	@Column(updatable = true)
	private LocalDateTime createdAt = LocalDateTime.now();
}
