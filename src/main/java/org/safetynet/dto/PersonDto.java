package org.safetynet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Setter;

public record PersonDto(@NotBlank(message = "First name is mandatory") @Setter(AccessLevel.NONE) String firstName,
                        @NotBlank(message = "Last name is mandatory") @Setter(AccessLevel.NONE) String lastName,
                        String address,
                        String city,
                        String zip,
                        String phone,
                        String email) {
}
