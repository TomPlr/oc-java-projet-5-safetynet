package org.safetynet.repository.impl;

import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.MEDICAL_RECORDS_ENTITIES;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {
    @Override
    public List<MedicalRecordEntity> findAll() {
        return MEDICAL_RECORDS_ENTITIES;
    }

    @Override
    public MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity) {
        MEDICAL_RECORDS_ENTITIES.add(medicalRecordEntity);

        Optional<MedicalRecordEntity> optionalMedicalRecordEntity = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.equals(medicalRecordEntity))
                .findFirst();

        return optionalMedicalRecordEntity.orElseThrow(() -> new RuntimeException("MedicalRecordEntity not found"));
    }

    @Override
    public MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity) {
        Optional<MedicalRecordEntity> optionalMedicalRecordEntity = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(medicalRecordEntity.getFirstName()) || medicalRecord.getLastName().equals(medicalRecordEntity.getLastName()) )
                .findFirst();

        return optionalMedicalRecordEntity
                .map(medicalRecord -> {
                    medicalRecord.setMedications(medicalRecordEntity.getMedications());
                    medicalRecord.setBirthdate(medicalRecordEntity.getBirthdate());
                    medicalRecord.setAllergies(medicalRecordEntity.getAllergies());

                    return medicalRecord;})
                .orElseThrow(() -> new RuntimeException("MedicalRecordEntity not found"));

    }

    @Override
    public void delete(String firstName, String lastName) {
        MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecordEntity -> medicalRecordEntity.getFirstName().equals(firstName) || medicalRecordEntity.getLastName().equals(lastName))
                .findFirst()
                .ifPresentOrElse(MEDICAL_RECORDS_ENTITIES::remove,
                        () -> {throw new RuntimeException("MedicalRecordEntity not found");
                });
    }
}
