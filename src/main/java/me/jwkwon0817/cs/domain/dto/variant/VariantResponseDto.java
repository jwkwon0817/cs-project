package me.jwkwon0817.cs.domain.dto.variant;

import lombok.*;
import me.jwkwon0817.cs.domain.enums.Filter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VariantResponseDto {
	
	private Long id;
	private Long patientId;
	private String variantId;
	
	
	private String chromosome;
	
	private Long position;
	
	private String reference;
	
	private String alternate;
	
	private String quality;
	
	private Filter filter;
	
	private String gene;
	
	private String genoType;
	
	private String genoTypeQuality;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
}
