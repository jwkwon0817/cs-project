package me.jwkwon0817.cs.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.cs.domain.enums.Gender;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDto {
	
	private Long id;
	
	private String orderName;
	
	private String name;
	
	private String hospitalName;
	
	private int age;
	
	private Gender gender;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
}
