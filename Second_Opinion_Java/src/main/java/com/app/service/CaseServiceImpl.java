package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.CaseDTO;
import com.app.entity.CaseEntity;
import com.app.entity.DoctorEntity;
import com.app.entity.PatientEntity;
import com.app.repository.CaseRepository;
import com.app.repository.DoctorRepository;
import com.app.repository.PatientRepository;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {
	
	@Autowired
	CaseRepository caseRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<CaseDTO> getCasesByPatientId(Long id) {
		List<CaseEntity> entityCases = caseRepository.findAllByPatientId(id);
		List<CaseDTO> dtoCases = new ArrayList<CaseDTO>();
		for(CaseEntity e : entityCases)
		{
			CaseDTO c = mapper.map(e, CaseDTO.class);
			dtoCases.add(c);
		}
		return dtoCases;
	}

	@Override
	public List<CaseDTO> getCasesByDoctorId(Long id) {

		List<CaseEntity> entityCases = caseRepository.findAllByDoctorId(id);
		List<CaseDTO> dtoCases = new ArrayList<CaseDTO>();
		for(CaseEntity e : entityCases)
		{
			CaseDTO c = mapper.map(e, CaseDTO.class);
			dtoCases.add(c);
		}
		return dtoCases;
	}

	@Override
	public CaseDTO createCase(CaseDTO c) {
		PatientEntity patient = patientRepository.findById(c.getPatientId()).orElseThrow(()->new ResourceNotFoundException("Patient not exists"));
		DoctorEntity doctor = doctorRepository.findById(c.getDoctorId()).orElseThrow(()->new ResourceNotFoundException("Doctor not exists"));
		CaseEntity newCase = mapper.map(c, CaseEntity.class);
		doctor.addCase(newCase);
		patient.addCase(newCase);
		return mapper.map(caseRepository.save(newCase), CaseDTO.class);
	}

	@Override
	public void deleteCase(Long id) {
		caseRepository.deleteById(id);
	}

	@Override
	public CaseDTO getCasesById(Long id) {
		return mapper.map(caseRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Case Not Found")), CaseDTO.class);
	}

}
