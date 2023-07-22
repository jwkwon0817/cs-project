package me.jwkwon0817.cs.domain.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.jwkwon0817.cs.domain.dto.file.PatientFileRequestDto;
import me.jwkwon0817.cs.domain.dto.file.PatientFileResponseDto;
import me.jwkwon0817.cs.domain.entities.Patient;
import me.jwkwon0817.cs.domain.entities.PatientFile;
import me.jwkwon0817.cs.domain.repositories.PatientFileRepository;
import me.jwkwon0817.cs.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientFileService {
	
	@Value("${filePath}")
	private String filePath;
	
	private final PatientRepository patientRepository;
	private final PatientFileRepository patientFileRepository;
	
	public PatientFileResponseDto create(final Long patientId, MultipartFile file) {
		File f = new File(filePath + randomFileName());
		
		try {
			file.transferTo(f);
		} catch (IOException ex) {
			return null;
		}
		
		String realFileName = file.getOriginalFilename();
		String fileName = f.getName();
		String filePath = f.getPath();
		
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		
		PatientFileRequestDto dto = new PatientFileRequestDto(patientId, realFileName);
		PatientFile patientFile = dto.toEntity();
		patientFile.setFile(fileName, filePath);
		patientFile.setPatient(patient);
		
		PatientFile save = patientFileRepository.save(patientFile);
		
		return save.toDto();
	}
	
	public PatientFileResponseDto createByOrderName(final String orderName, MultipartFile file) {
		File f = new File(filePath + randomFileName());
		
		try {
			file.transferTo(f);
		} catch (IOException ex) {
			return null;
		}
		
		String realFileName = file.getOriginalFilename();
		String fileName = f.getName();
		String filePath = f.getPath();
		
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFileRequestDto dto = new PatientFileRequestDto(patient.getId(), realFileName);
		PatientFile patientFile = dto.toEntity();
		patientFile.setFile(fileName, filePath);
		
		PatientFile save = patientFileRepository.save(patientFile);
		
		return save.toDto();
	}
	
	public PatientFileResponseDto update(final Long patientId, MultipartFile file) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		PatientFile patientFile = patient.getPatientFile();
		
		patientFile.update(file.getOriginalFilename());
		patientFileRepository.save(patientFile);
		
		File f = new File(filePath + patientFile.getFileName());
		
		try {
			file.transferTo(f);
		} catch (IOException ex) {
			return null;
		}
		
		return patientFile.toDto();
	}
	
	public PatientFileResponseDto updateByOrderName(final String orderName, MultipartFile file) {
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFile patientFile = patient.getPatientFile();
		
		patientFile.update(file.getOriginalFilename());
		patientFileRepository.save(patientFile);
		
		File f = new File(filePath + patientFile.getFileName());
		
		try {
			file.transferTo(f);
		} catch (IOException ex) {
			return null;
		}
		
		return patientFile.toDto();
	}
	
	public void delete(final Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		PatientFile patientFile = patient.getPatientFile();
		
		patientFileRepository.delete(patientFile);
		patientFileRepository.flush();
		
		File f = new File(filePath + patientFile.getFileName());
		boolean delete = f.delete();
		
		if (!delete) {
			throw new RuntimeException("파일 삭제 실패");
		}
	}
	
	public void deleteByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFile patientFile = patient.getPatientFile();
		
		patientFileRepository.delete(patientFile);
		
		File f = new File(filePath + patientFile.getFileName());
		boolean delete = f.delete();
		
		if (!delete) {
			throw new RuntimeException("파일 삭제 실패");
		}
	}
	
	public PatientFileResponseDto retrieve(final Long patientId) {
		Patient patient = patientRepository.findById(patientId).orElseThrow(EntityNotFoundException::new);
		PatientFile patientFile = patient.getPatientFile();
		
		return patientFile.toDto();
	}
	
	public PatientFileResponseDto retrieveByOrderName(final String orderName) {
		Patient patient = patientRepository.findByOrderName(orderName);
		PatientFile patientFile = patient.getPatientFile();
		
		return patientFile.toDto();
	}
	
	public List<PatientFileResponseDto> retrieveAll() {
		return patientFileRepository.findAll().stream().map(PatientFile::toDto).toList();
	}
	
	private String randomFileName() {
		return UUID.randomUUID() + ".tsv";
	}
}
