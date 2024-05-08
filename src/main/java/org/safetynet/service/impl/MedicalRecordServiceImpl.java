package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository repository;

    @Override
    public List<MedicalRecordEntity> findAll() throws IOException {
        return repository.findAll();
    }

    @Override
    public MedicalRecordEntity save(MedicalRecordEntity medicalRecord) throws IOException {
        return repository.save(medicalRecord);
    }

    @Override
    public MedicalRecordEntity update(MedicalRecordEntity medicalRecord) throws IOException {
        return repository.update(medicalRecord);
    }

    @Override
    public GenericResponseDto delete(String firstName, String lastName) throws IOException {
        final boolean isSuccessfullyDeleted = repository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseDto(true, String.format("The medical record for %s %s has been successfully deleted !", firstName, lastName));

        } else {
            return new GenericResponseDto(false, String.format("Medical record for %s %s not found", firstName, lastName));
        }
    }
}
