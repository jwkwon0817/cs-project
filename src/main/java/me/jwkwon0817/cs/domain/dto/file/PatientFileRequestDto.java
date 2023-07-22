package me.jwkwon0817.cs.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.cs.domain.entities.PatientFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PatientFileRequestDto {
	
	private Long patientId;
	
	private String realFileName;
	
	public PatientFile toEntity() {
		return PatientFile.builder()
			.realFileName(realFileName)
			.build();
	}
}
