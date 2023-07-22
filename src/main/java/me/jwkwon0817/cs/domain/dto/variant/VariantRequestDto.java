package me.jwkwon0817.cs.domain.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jwkwon0817.cs.domain.entities.Patient;
import me.jwkwon0817.cs.domain.entities.Variant;
import me.jwkwon0817.cs.domain.enums.Filter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantRequestDto {
	
	private String variantId;
	
	private Long patientId;
	
	private String chromosome;
	
	private Long position;
	
	private String reference;
	
	private String alternate;
	
	private String quality;
	
	private Filter filter;
	
	private String gene;
	
	private String genoType;
	
	private String genoTypeQuality;
	
	public Variant toEntity() {
		Patient patient = Patient.builder()
			.id(patientId)
			.build();
		
		return Variant.builder()
			.variantId(variantId)
			.patient(patient)
			.chromosome(chromosome)
			.position(position)
			.reference(reference)
			.alternate(alternate)
			.quality(quality)
			.filter(filter)
			.gene(gene)
			.genoType(genoType)
			.genoTypeQuality(genoTypeQuality)
			.build();
	}
}
