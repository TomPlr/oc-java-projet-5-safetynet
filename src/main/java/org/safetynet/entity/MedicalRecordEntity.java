package org.safetynet.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Getter
@Setter
@Builder
public class MedicalRecordEntity {

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

}
