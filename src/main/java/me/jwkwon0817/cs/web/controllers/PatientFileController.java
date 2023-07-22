package me.jwkwon0817.cs.web.controllers;

import lombok.RequiredArgsConstructor;
import me.jwkwon0817.cs.domain.services.PatientFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientFileController {
	
	private final PatientFileService patientFileService;
	
	@GetMapping("/patientFile/{patientId}")
	public ResponseEntity<?> getPatientFile(@PathVariable final Long patientId) {
		return ResponseEntity.ok(patientFileService.retrieve(patientId));
	}
	
	@GetMapping("/patientFile/{orderName}/orderName")
	public ResponseEntity<?> getPatientFileByOrderName(@PathVariable final String orderName) {
		return ResponseEntity.ok(patientFileService.retrieveByOrderName(orderName));
	}
	
	@PostMapping("/patientFile/{patientId}")
	public ResponseEntity<?> createPatientFile(@PathVariable final Long patientId, @RequestParam final MultipartFile file) {
		return ResponseEntity.ok(patientFileService.create(patientId, file));
	}
	
	@PostMapping("/patientFile/{orderName}/orderName")
	public ResponseEntity<?> createPatientFileByOrderName(@PathVariable final String orderName, @RequestParam final MultipartFile file) {
		return ResponseEntity.ok(patientFileService.createByOrderName(orderName, file));
	}
	
	@PutMapping("/patientFile/{patientId}")
	public ResponseEntity<?> updatePatientFile(@PathVariable final Long patientId, @RequestParam final MultipartFile file) {
		return ResponseEntity.ok(patientFileService.update(patientId, file));
	}
	
	@PutMapping("/patientFile/{orderName}/orderName")
	public ResponseEntity<?> updatePatientFileByOrderName(@PathVariable final String orderName, @RequestParam final MultipartFile file) {
		return ResponseEntity.ok(patientFileService.updateByOrderName(orderName, file));
	}
	
	@DeleteMapping("/patientFile/{patientId}")
	public ResponseEntity<?> deletePatientFile(@PathVariable final Long patientId) {
		patientFileService.delete(patientId);
		return ResponseEntity.ok("Successfully deleted. patientId=" + patientId);
	}
	
	@DeleteMapping("/patientFile/{orderName}/orderName")
	public ResponseEntity<?> deletePatientFileByOrderName(@PathVariable final String orderName) {
		patientFileService.deleteByOrderName(orderName);
		return ResponseEntity.ok("Successfully deleted. orderName=" + orderName);
	}
	
	@GetMapping("/patientFiles")
	public ResponseEntity<?> getPatientFiles() {
		return ResponseEntity.ok(patientFileService.retrieveAll());
	}
}
