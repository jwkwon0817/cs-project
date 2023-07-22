package me.jwkwon0817.cs.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jwkwon0817.cs.domain.dto.patient.PatientRequestDto;
import me.jwkwon0817.cs.domain.dto.patient.PatientResponseDto;
import me.jwkwon0817.cs.domain.entities.Patient;
import me.jwkwon0817.cs.domain.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
	
	private final PatientRepository patientRepository;
	
	public PatientResponseDto create(final PatientRequestDto dto) {
		return patientRepository.save(dto.toEntity()).toDto();
	}
	
	public PatientResponseDto update(final Long id, final PatientRequestDto dto) {
		Patient patient = patientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		patient.update(dto);
		patientRepository.save(patient);
		
		return patient.toDto();
	}
	
	@Transactional
	public PatientResponseDto updateByOrderName(final String orderName, final PatientRequestDto dto) {
		Patient patient = patientRepository.findByOrderName(orderName);
		patient.update(dto);
		patientRepository.save(patient);
		
		return patient.toDto();
	}
	
	public void delete(final Long id) {
		patientRepository.deleteById(id);
	}
	
	public void deleteByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		
		patientRepository.delete(patient);
	}
	
	public PatientResponseDto retrieve(final Long id) {
		return patientRepository.findById(id).orElseThrow(EntityNotFoundException::new).toDto();
	}
	
	public PatientResponseDto retrieveByOrderName(final String orderName) {
		return patientRepository.findByOrderName(orderName).toDto();
	}
	
	public List<PatientResponseDto> retrieveAll() {
		return patientRepository.findAll().stream().map(Patient::toDto).toList();
	}
}
