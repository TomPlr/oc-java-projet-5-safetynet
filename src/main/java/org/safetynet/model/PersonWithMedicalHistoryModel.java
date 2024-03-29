package org.safetynet.model;

public record PersonWithMedicalHistoryModel(String firstName, String lastName, String phone, int age,
                                            MedicalHistoryModel medicalHistory) {
}
