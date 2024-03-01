package org.safetynet.repository;

import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;

import java.util.List;

public interface MedicalRecordRepository {
    /**
     * Get list of all persons
     *
     * @return List<MedicalRecordEntity>
     */
    List<MedicalRecordEntity> findAll();

    /**
     * Create a new medical record
     *
     * @param medicalRecordEntity medicalRecordEntity
     * @return
     */
    MedicalRecordDto save(MedicalRecordEntity medicalRecordEntity);

    /**
     * Update a medical record
     *
     * @param medicalRecordEntity MedicalRecordEntity
     * @return
     */
    MedicalRecordDto update(MedicalRecordEntity medicalRecordEntity);

    /**
     * Delete a medical record
     *
     * @param firstName String
     * @param lastName  String
     */
    boolean delete(String firstName, String lastName);
}