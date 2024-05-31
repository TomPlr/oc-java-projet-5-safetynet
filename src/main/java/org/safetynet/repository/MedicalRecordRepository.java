package org.safetynet.repository;

import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;

import java.util.List;

public interface MedicalRecordRepository {
    /**
     * Get list of all persons
     *
     * @return List<MedicalRecordDto>
     */
    List<MedicalRecordDto> findAll();

    /**
     * Create a new medical record
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
     */
    boolean delete(String firstName, String lastName);

    /**
     * Get a medical record
     *
     * @param  firstName String
     * @param lastName String
     *
     * @return MedicalRecordDto
     */
    MedicalRecordDto findMedicalRecordByName(String firstName, String lastName);

    /**
     * Get medical records of a list of persons
     *
     * @param persons List<PersonEntity>
     */
    List<MedicalRecordDto> findMedicalRecordsByPersons(List<PersonDto> persons);
}
