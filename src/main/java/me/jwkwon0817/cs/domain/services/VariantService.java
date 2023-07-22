package me.jwkwon0817.cs.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jwkwon0817.cs.domain.dto.variant.VariantRequestDto;
import me.jwkwon0817.cs.domain.dto.variant.VariantResponseDto;
import me.jwkwon0817.cs.domain.entities.Patient;
import me.jwkwon0817.cs.domain.entities.PatientFile;
import me.jwkwon0817.cs.domain.entities.Variant;
import me.jwkwon0817.cs.domain.enums.Filter;
import me.jwkwon0817.cs.domain.repositories.PatientRepository;
import me.jwkwon0817.cs.domain.repositories.VariantRepository;
import me.jwkwon0817.cs.utils.VariantFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VariantService {
	
	private final PatientRepository patientRepository;
	private final VariantRepository variantRepository;
	
	public List<VariantResponseDto> create(final Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		PatientFile patientFile = patient.getPatientFile();
		
		String[] data = VariantFilter.grepTsvData(patientFile.getFilePath()).split("\n");
		
		List<VariantResponseDto> dtoList = new ArrayList<>();
		
		for (String datum : data) {
			String[] line = datum.split("\t");
			
			VariantRequestDto build = VariantRequestDto.builder()
				.chromosome(line[0])
				.position(Long.valueOf(line[1]))
				.variantId(line[2])
				.reference(line[3])
				.alternate(line[4])
				.quality(line[5])
				.filter(Filter.valueOf(line[6]))
				.gene(line[7])
				.genoType(line[8])
				.genoTypeQuality(line[9])
				.build();
			
			Variant variant = build.toEntity();
			variant.setPatient(patient);
			
			Variant save = variantRepository.save(variant);
			
			dtoList.add(save.toDto());
		}
		
		return dtoList;
	}
	
	public List<VariantResponseDto> createByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFile patientFile = patient.getPatientFile();
		
		String[] data = VariantFilter.grepTsvData(patientFile.getFilePath()).split("\n");
		
		List<VariantResponseDto> dtoList = new ArrayList<>();
		
		for (String datum : data) {
			String[] line = datum.split("\t");
			
			VariantRequestDto build = VariantRequestDto.builder()
				.chromosome(line[0])
				.position(Long.valueOf(line[1]))
				.variantId(line[2])
				.reference(line[3])
				.alternate(line[4])
				.quality(line[5])
				.filter(Filter.valueOf(line[6]))
				.gene(line[7])
				.genoType(line[8])
				.genoTypeQuality(line[9])
				.build();
			
			Variant variant = build.toEntity();
			variant.setPatient(patient);
			
			Variant save = variantRepository.save(variant);
			
			dtoList.add(save.toDto());
		}
		
		return dtoList;
	}
	
	public List<VariantResponseDto> update(final Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		PatientFile patientFile = patient.getPatientFile();
		
		String[] data = VariantFilter.grepTsvData(patientFile.getFilePath()).split("\n");
		
		variantRepository.deleteAll(patient.getVariants());
		variantRepository.flush();
		
		List<VariantResponseDto> dtoList = new ArrayList<>();
		
		for (String datum : data) {
			String[] line = datum.split("\t");
			
			VariantRequestDto build = VariantRequestDto.builder()
				.chromosome(line[0])
				.position(Long.valueOf(line[1]))
				.variantId(line[2])
				.reference(line[3])
				.alternate(line[4])
				.quality(line[5])
				.filter(Filter.valueOf(line[6]))
				.gene(line[7])
				.genoType(line[8])
				.genoTypeQuality(line[9])
				.build();
			
			Variant variant = build.toEntity();
			variant.setPatient(patient);
			
			Variant save = variantRepository.save(variant);
			
			dtoList.add(save.toDto());
		}
		
		return dtoList;
	}
	
	public List<VariantResponseDto> updateByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFile patientFile = patient.getPatientFile();
		
		String[] data = VariantFilter.grepTsvData(patientFile.getFilePath()).split("\n");
		
		variantRepository.deleteAll(patient.getVariants());
		variantRepository.flush();
		
		List<VariantResponseDto> dtoList = new ArrayList<>();
		
		for (String datum : data) {
			String[] line = datum.split("\t");
			
			VariantRequestDto build = VariantRequestDto.builder()
				.chromosome(line[0])
				.position(Long.valueOf(line[1]))
				.variantId(line[2])
				.reference(line[3])
				.alternate(line[4])
				.quality(line[5])
				.filter(Filter.valueOf(line[6]))
				.gene(line[7])
				.genoType(line[8])
				.genoTypeQuality(line[9])
				.build();
			
			Variant variant = build.toEntity();
			variant.setPatient(patient);
			
			Variant save = variantRepository.save(variant);
			
			dtoList.add(save.toDto());
		}
		
		return dtoList;
	}
	
	public void delete(Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		variantRepository.deleteAll(patient.getVariants());
	}
	
	public void deleteByOrderName(String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		variantRepository.deleteAll(patient.getVariants());
	}
	
	public List<VariantResponseDto> retrieveAll(final Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		List<Variant> variants = patient.getVariants();
		
		return variants.stream().map(Variant::toDto).toList();
	}
	
	public List<VariantResponseDto> retrieveAllByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		List<Variant> variants = patient.getVariants();
		
		return variants.stream().map(Variant::toDto).toList();
	}
}
