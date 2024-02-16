package org.safetynet.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
public class PersonEntity {

	@NotBlank(message = "Firstname is mandatory")
	@Setter(AccessLevel.NONE)
	private String firstName;

	@NotBlank(message = "Lastname is mandatory")
	@Setter(AccessLevel.NONE)
	private String lastName;

	private String address;

	private String city;

	private String zip;

	private String phone;

	private String email;
}
