package com.app.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "testimonials")
public class TestimonialEntity extends BaseEntity {
	@Lob
	@Column(nullable = false)
	private String testimony;
	
	@Column(nullable = false)
	private int rating;
	
	@Column(nullable = false,name="created_at")
	private LocalDateTime createdAt;
	
	// Testimonial *<--->1 Patient
	@ManyToOne
	@JoinColumn(name = "patient_id",nullable = false)
	private PatientEntity patient;
}
