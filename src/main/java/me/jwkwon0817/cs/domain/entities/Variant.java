package me.jwkwon0817.cs.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import me.jwkwon0817.cs.domain.dto.variant.VariantResponseDto;
import me.jwkwon0817.cs.domain.enums.Filter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Variant extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String variantId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@Setter
	private Patient patient;
	
	private String chromosome;
	
	private Long position;
	
	private String reference;
	
	private String alternate;
	
	private String quality;
	
	private Filter filter;
	
	private String gene;
	
	private String genoType;
	
	private String genoTypeQuality;
	
	public VariantResponseDto toDto() {
		return VariantResponseDto.builder()
			.id(id)
			.variantId(variantId)
			.patientId(patient.getId())
			.chromosome(chromosome)
			.position(position)
			.reference(reference)
			.alternate(alternate)
			.quality(quality)
			.filter(filter)
			.gene(gene)
			.genoType(genoType)
			.genoTypeQuality(genoTypeQuality)
			.createdDate(super.getCreatedDate())
			.modifiedDate(super.getModifiedDate())
			.build();
	}
}
