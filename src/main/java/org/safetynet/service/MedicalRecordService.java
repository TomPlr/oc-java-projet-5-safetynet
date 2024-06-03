package org.safetynet.service;

import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.MedicalRecordDto;

import java.util.List;

public interface MedicalRecordService {
    /**
     * Find all medical records
     *
     * @return MedicalRecordDto
     */
    List<MedicalRecordDto> findAll();

    /**
     * Save a new medical record
     *
     * @param medicalRecord MedicalRecordDto
     * @return MedicalRecordDto
     */
    MedicalRecordDto save(MedicalRecordDto medicalRecord);

    /**
     * Update a medical record
     *
     * @param medicalRecord MedicalRecordDto
     * @return MedicalRecordDto
     */
    MedicalRecordDto update(MedicalRecordDto medicalRecord);

    /**
     * Delete a medical record
     *
     * @param firstName String
     * @param lastName  String
     * @return GenericResponseDto
     */
    GenericResponseDto delete(String firstName, String lastName);
}
