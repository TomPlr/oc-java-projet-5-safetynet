package org.safetynet.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @Min(1)
    private int station;
}
