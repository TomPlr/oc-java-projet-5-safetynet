package org.safetynet.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
public class FireStationEntity {
	@NotBlank(message = "Address is mandatory")
	private String address;

	@NotNull(message = "Station is mandatory")
	private int station;
}
