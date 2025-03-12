package com.example.demo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "AppartmentImages")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class AppartmentImages {
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column
	private int appartmentId;
	@Column
	private String imageUrl;
	@Column
	@CreatedDate
	private Timestamp createdAt;

}
