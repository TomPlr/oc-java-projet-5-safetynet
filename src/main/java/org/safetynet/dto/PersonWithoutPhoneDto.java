package org.safetynet.dto;

import org.safetynet.model.MedicalHistoryModel;

public record PersonWithoutPhoneDto(String firstName, String lastName, String address, String email, int age,
                                    MedicalHistoryModel medicalHistory) {
}
