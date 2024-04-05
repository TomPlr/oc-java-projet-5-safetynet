package org.safetynet.dto;

import org.safetynet.model.MedicalHistoryModel;

public record PersonWithoutAddressAndEmailDto(String firstName, String lastName, String phone, int age,
                                              MedicalHistoryModel medicalHistory) {
}
