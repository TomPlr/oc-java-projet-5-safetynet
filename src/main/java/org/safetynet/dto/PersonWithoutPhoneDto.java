package org.safetynet.dto;

public record PersonWithoutPhoneDto(String firstName, String lastName, String address, String email, int age,
                                    MedicalHistoryDto medicalHistory) {
}
