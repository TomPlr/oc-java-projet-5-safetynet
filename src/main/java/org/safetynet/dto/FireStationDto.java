package org.safetynet.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record FireStationDto(@NotBlank(message = "Address is mandatory") String address,
                             @Min(value = 1, message = "Station number must be equal or up to 1") int station) {
}
