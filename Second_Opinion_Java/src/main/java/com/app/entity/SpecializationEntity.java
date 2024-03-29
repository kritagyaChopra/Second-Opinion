/**
 * 
 */
package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "specializations")
public class SpecializationEntity extends BaseEntity {
	
	@Column(length = 30,nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "specialization" , cascade = CascadeType.ALL , orphanRemoval = true,fetch = FetchType.EAGER)
	private List<DoctorEntity> doctors = new ArrayList<DoctorEntity>();
	
	@OneToOne(mappedBy = "specialization")
	private DiseaseEntity disease;
	
	public void addDoctor(DoctorEntity doctor)
	{
		this.doctors.add(doctor);
		doctor.setSpecialization(this);
	}
	public void removeDoctor(DoctorEntity doctor)
	{
		this.doctors.remove(doctor);
		doctor.setSpecialization(null);
	}
}
