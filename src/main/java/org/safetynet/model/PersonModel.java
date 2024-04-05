package org.safetynet.model;

import org.safetynet.dto.PersonDto;

import java.util.List;

public record PersonModel(String firstName, String lastName, String address, String email, int age, String phone,
                          MedicalHistoryModel medicalHistory, List<PersonDto> familyMembers) {
}
