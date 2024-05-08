package org.safetynet.dto;

import java.util.List;

public record PersonExtendedDto(String firstName, String lastName, String address, String email, int age, String phone,
                                MedicalHistoryDto medicalHistory, List<PersonDto> familyMembers) {
}

