package org.safetynet.dto;

public record PersonWithoutAddressAndEmailDto(String firstName, String lastName, String phone, int age,
                                              MedicalHistoryDto medicalHistory) {
}
