package org.safetynet.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Getter
@Setter
@Builder
public class FireStationEntity {
    private String address;
    private int station;
}
