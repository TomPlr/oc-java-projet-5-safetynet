package org.safetynet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalRecordDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String birthdate;
    private List<String> medications;
    private List<String> allergies;
}
