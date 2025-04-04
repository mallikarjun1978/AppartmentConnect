package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "facilities")
@Data                         // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor           // No-args constructor
@AllArgsConstructor          // All-args constructor
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private int facilityId;

    @Column(name = "facility_name", nullable = false)
    private String facilityName;

    @Column(name = "description")
    private String description;

    @Column(name = "available_time", nullable = false)
    private String availableTime;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive = true;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}
