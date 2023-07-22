package me.jwkwon0817.cs.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.cs.domain.entities.Patient;
import me.jwkwon0817.cs.domain.enums.Gender;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {
	
	private String orderName;
	
	private String name;
	
	private String hospitalName;
	
	private int age;
	
	private Gender gender;
	
	public Patient toEntity() {
		return Patient.builder()
			.orderName(orderName)
			.name(name)
			.hospitalName(hospitalName)
			.age(age)
			.gender(gender)
			.build();
	}
}
