package org.safetynet.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Person {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
}
