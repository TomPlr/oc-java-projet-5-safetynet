package org.safetynet.model;

public record PersonModel(String firstName, String lastName, String address, String email, int age, String phone,
                          MedicalHistoryModel medicalHistory) {
}
