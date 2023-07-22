package me.jwkwon0817.cs.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import me.jwkwon0817.cs.domain.dto.file.PatientFileResponseDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PatientFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String realFileName;
	
	@Column(unique = true)
	private String fileName;
	
	@Column(unique = true)
	private String filePath;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@Setter
	private Patient patient;
	
	public void update(String realFileName) {
		this.realFileName = realFileName;
	}
	
	public void setFile(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public PatientFileResponseDto toDto() {
		return PatientFileResponseDto.builder()
			.id(id)
			.patientId(patient.getId())
			.realFileName(realFileName)
			.fileName(fileName)
			.filePath(filePath)
			.build();
	}
}
