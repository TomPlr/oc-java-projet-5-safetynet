package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.MEDICAL_RECORDS_ENTITIES;

@Repository
@AllArgsConstructor
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    private final MedicalRecordMapper mapper;

    @Override
    public List<MedicalRecordEntity> findAll() {
        return MEDICAL_RECORDS_ENTITIES;
    }

    @Override
    public MedicalRecordDto save(MedicalRecordEntity medicalRecordEntity) {
        MEDICAL_RECORDS_ENTITIES.add(medicalRecordEntity);

        Optional<MedicalRecordDto> optionalMedicalRecord = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.equals(medicalRecordEntity))
                .findFirst()
                .map(mapper::medicalRecordEntityToDto);

        return optionalMedicalRecord.orElseThrow(() -> new NoSuchElementException("MedicalRecordEntity not found"));
    }

    @Override
    public MedicalRecordDto update(MedicalRecordEntity medicalRecordEntity) {
        Optional<MedicalRecordEntity> optionalMedicalRecordEntity = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(medicalRecordEntity.getFirstName()) || medicalRecord.getLastName().equals(medicalRecordEntity.getLastName()))
                .findFirst();

        return optionalMedicalRecordEntity
                .map(medicalRecord -> {
                    medicalRecord.setMedications(medicalRecordEntity.getMedications());
                    medicalRecord.setBirthdate(medicalRecordEntity.getBirthdate());
                    medicalRecord.setAllergies(medicalRecordEntity.getAllergies());

                    return mapper.medicalRecordEntityToDto(medicalRecord);
                })
                .orElseThrow(() -> new NoSuchElementException("MedicalRecordEntity not found"));

    }

    @Override
    public boolean delete(String firstName, String lastName) {
        return MEDICAL_RECORDS_ENTITIES
                .removeIf(medicalRecord -> medicalRecord.getFirstName().equalsIgnoreCase(firstName) && medicalRecord.getLastName().equalsIgnoreCase(lastName));

    }
}
