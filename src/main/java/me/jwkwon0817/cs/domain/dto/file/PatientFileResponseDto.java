package me.jwkwon0817.cs.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PatientFileResponseDto {
	
	private Long id;
	
	private Long patientId;
	
	private String realFileName;
	private String fileName;
	
	private String filePath;
}
