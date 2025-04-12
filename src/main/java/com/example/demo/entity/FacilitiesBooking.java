package com.example.demo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name="facilitiesbooking")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // Ensure auditing is enabled
@ToString
public class FacilitiesBooking {
	@Id
	@Column(name="facility_bookingId",nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int facility_bookingId;
	@Column(name="facility_name",nullable=true)
	private String facility_name;
	@Column(name="available_time",nullable=true)
	private String available_time;
	@Column(name="resident_id",nullable=true)
	private int residentId;
	@Column(name="booking_status",nullable=true)
	private String booking_status;
	@Column(name="booking_date",nullable=true)
	@CreatedDate
	private Timestamp booking_date;
	@Column(name="created_at",nullable=true)
	@CreatedDate
	private Timestamp created_at;
	@Column(name="updated_at",nullable=true)
	 @LastModifiedDate
	private Timestamp updated_at;
	
	
	
}
