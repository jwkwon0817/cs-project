package me.jwkwon0817.cs.web.controllers;

import lombok.RequiredArgsConstructor;
import me.jwkwon0817.cs.domain.services.VariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VariantController {
	
	private final VariantService variantService;
	
	@GetMapping("/variants/{patientId}")
	public ResponseEntity<?> getVariants(@PathVariable final Long patientId) {
		return ResponseEntity.ok(variantService.retrieveAll(patientId));
	}
	
	@GetMapping("/variants/{orderName}/orderName")
	public ResponseEntity<?> getVariantsByOrderName(@PathVariable final String orderName) {
		return ResponseEntity.ok(variantService.retrieveAllByOrderName(orderName));
	}
	
	@PostMapping("/variants/{patientId}")
	public ResponseEntity<?> createVariant(@PathVariable final Long patientId) {
		return ResponseEntity.ok(variantService.create(patientId));
	}
	
	@PostMapping("/variants/{orderName}/orderName")
	public ResponseEntity<?> createVariantByOrderName(@PathVariable final String orderName) {
		return ResponseEntity.ok(variantService.createByOrderName(orderName));
	}
	
	@PutMapping("/variants/{patientId}")
	public ResponseEntity<?> updateVariant(@PathVariable final Long patientId) {
		return ResponseEntity.ok(variantService.update(patientId));
	}
	
	@PutMapping("/variants/{orderName}/orderName")
	public ResponseEntity<?> updateVariantByOrderName(@PathVariable final String orderName) {
		return ResponseEntity.ok(variantService.updateByOrderName(orderName));
	}
	
	@DeleteMapping("/variants/{patientId}")
	public ResponseEntity<?> deleteVariant(@PathVariable final Long patientId) {
		variantService.delete(patientId);
		return ResponseEntity.ok("Successfully deleted. patientId=" + patientId);
	}
	
	@DeleteMapping("/variants/{orderName}/orderName")
	public ResponseEntity<?> deleteVariantByOrderName(@PathVariable final String orderName) {
		variantService.deleteByOrderName(orderName);
		return ResponseEntity.ok("Successfully deleted. orderName=" + orderName);
	}
}
