package org.safetynet.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Builder
public class FireStationEntity {

	private String address;
	private String station;
}
