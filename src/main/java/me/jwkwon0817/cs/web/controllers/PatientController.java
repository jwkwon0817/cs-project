package me.jwkwon0817.cs.web.controllers;

import lombok.RequiredArgsConstructor;
import me.jwkwon0817.cs.domain.dto.patient.PatientRequestDto;
import me.jwkwon0817.cs.domain.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PatientController {
	
	private final PatientService patientService;
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<?> getPatient(@PathVariable final Long id) {
		return ResponseEntity.ok(patientService.retrieve(id));
	}
	
	@GetMapping("/patient/{orderName}/orderName")
	public ResponseEntity<?> getPatientByOrderName(@PathVariable final String orderName) {
		return ResponseEntity.ok(patientService.retrieveByOrderName(orderName));
	}
	
	@PostMapping("/patient")
	public ResponseEntity<?> createPatient(@RequestBody final PatientRequestDto dto) {
		return ResponseEntity.ok(patientService.create(dto));
	}
	
	@PutMapping("/patient/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable final Long id, @RequestBody final PatientRequestDto dto) {
		return ResponseEntity.ok(patientService.update(id, dto));
	}
	
	@PutMapping("/patient/{orderName}/orderName")
	public ResponseEntity<?> updatePatient(@PathVariable final String orderName, @RequestBody final PatientRequestDto dto) {
		return ResponseEntity.ok(patientService.updateByOrderName(orderName, dto));
	}
	
	@DeleteMapping("/patient/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable final Long id) {
		patientService.delete(id);
		return ResponseEntity.ok("Successfully deleted. id=" + id);
	}
	
	@DeleteMapping("/patient/{orderName}/orderName")
	public ResponseEntity<?> deletePatient(@PathVariable final String orderName) {
		patientService.deleteByOrderName(orderName);
		return ResponseEntity.ok("Successfully deleted. orderName=" + orderName);
	}
	
	@GetMapping("/patients")
	public ResponseEntity<?> getPatients() {
		return ResponseEntity.ok(patientService.retrieveAll());
	}
}
