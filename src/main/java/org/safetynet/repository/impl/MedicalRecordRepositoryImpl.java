package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.MEDICAL_RECORDS_ENTITIES;

@Repository
@AllArgsConstructor
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

    MedicalRecordMapper medicalRecordMapper;

    @Override
    public List<MedicalRecordDto> findAll() {
        return MEDICAL_RECORDS_ENTITIES.stream().map(medicalRecord -> medicalRecordMapper.toMedicalRecordDto(medicalRecord)).toList();
    }

    @Override
    public MedicalRecordDto save(MedicalRecordDto medicalRecordDto) {
        MedicalRecordEntity medicalRecordEntity = medicalRecordMapper.toMedicalRecordEntity(medicalRecordDto);

        MEDICAL_RECORDS_ENTITIES.add(medicalRecordEntity);

        Optional<MedicalRecordDto> optionalMedicalRecord = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.equals(medicalRecordEntity))
                .map(medicalRecord -> medicalRecordMapper.toMedicalRecordDto(medicalRecord))
                .findFirst();


        return optionalMedicalRecord.orElseThrow(() -> new NoSuchElementException("Medical record not found"));
    }

    @Override
    public MedicalRecordDto update(MedicalRecordDto medicalRecordDto) {
        Optional<MedicalRecordEntity> optionalMedicalRecordEntity = MEDICAL_RECORDS_ENTITIES
                .stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(medicalRecordDto.firstName()) && medicalRecord.getLastName().equals(medicalRecordDto.lastName()))
                .findFirst();

        return optionalMedicalRecordEntity
                .map(medicalRecord -> {
                    medicalRecord.setMedications(medicalRecordDto.medications());
                    medicalRecord.setBirthdate(medicalRecordDto.birthdate());
                    medicalRecord.setAllergies(medicalRecordDto.allergies());

                    return medicalRecordMapper.toMedicalRecordDto(medicalRecord);
                })
                .orElseThrow(() -> new NoSuchElementException("Medical record not found"));

    }

    @Override
    public boolean delete(String firstName, String lastName) {
        return MEDICAL_RECORDS_ENTITIES
                .removeIf(medicalRecord -> medicalRecord.getFirstName().equalsIgnoreCase(firstName) && medicalRecord.getLastName().equalsIgnoreCase(lastName));

    }

    @Override
    public MedicalRecordDto findMedicalRecordByName(String firstName, String lastName) {
        return MEDICAL_RECORDS_ENTITIES.stream()
                .filter(record -> record.getFirstName().equals(firstName)
                        && record.getLastName().equals(lastName))
                .findFirst()
                .map(medicalRecord -> medicalRecordMapper.toMedicalRecordDto(medicalRecord))
                .orElse(null);
    }

    @Override
    public List<MedicalRecordDto> findMedicalRecordsByPersons(List<PersonDto> persons) {
        List<MedicalRecordDto> medicalRecords = new ArrayList<>();

        for (PersonDto person : persons) {
            MEDICAL_RECORDS_ENTITIES.stream()
                    .filter(record -> record.getFirstName().equals(person.firstName())
                            && record.getLastName().equals(person.lastName()))
                    .findFirst()
                    .map(medicalRecord -> medicalRecordMapper.toMedicalRecordDto(medicalRecord))
                    .ifPresent(medicalRecords::add);
        }

        return medicalRecords;
    }
}
