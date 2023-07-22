package me.jwkwon0817.cs.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.cs.domain.dto.patient.PatientRequestDto;
import me.jwkwon0817.cs.domain.dto.patient.PatientResponseDto;
import me.jwkwon0817.cs.domain.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Patient extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private Long id;
	
	private String orderName;
	
	private String name;
	
	private String hospitalName;
	
	private int age;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Variant> variants = new ArrayList<>();
	
	@OneToOne(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private PatientFile patientFile;
	
	public void update(PatientRequestDto dto) {
		this.orderName = dto.getOrderName();
		this.name = dto.getName();
		this.hospitalName = dto.getHospitalName();
		this.age = dto.getAge();
		this.gender = dto.getGender();
	}
	
	public PatientResponseDto toDto() {
		return PatientResponseDto.builder()
			.id(id)
			.orderName(orderName)
			.name(name)
			.hospitalName(hospitalName)
			.age(age)
			.gender(gender)
			.createdDate(super.getCreatedDate())
			.modifiedDate(super.getModifiedDate())
			.build();
	}
}
