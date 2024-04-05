package org.safetynet.repository;

import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;

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

    /**
     * Get a medical record
     *
     * @param  firstName String
     * @param lastName String
     *
     */
    MedicalRecordEntity findMedicalRecordByName(String firstName, String lastName);

    /**
     * Get medical records of a list of persons
     *
     * @param persons List<PersonEntity>
     */
    List<MedicalRecordEntity> findMedicalRecordsByPersons(List<PersonEntity> persons);
}
