package org.safetynet.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record MedicalRecordDto(@NotBlank(message = "Firstname is mandatory")
                               String firstName,
                               @NotBlank(message = "Lastname is mandatory")
                               String lastName,
                               String birthdate,
                               List<String> medications,
                               List<String> allergies) {
}
