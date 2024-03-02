package org.safetynet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FireStationDto {

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Min(1)
    private int station;

}
