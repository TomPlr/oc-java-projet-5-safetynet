package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository repository;
    private final MedicalRecordMapper mapper;

    @Override
    public List<MedicalRecordEntity> findAll() throws IOException {
        return repository.findAll();
    }

    @Override
    public MedicalRecordDto save(MedicalRecordDto medicalRecord) throws IOException {
        return repository.save(mapper.medicalRecordDtoToEntity(medicalRecord));
    }

    @Override
    public MedicalRecordDto update(MedicalRecordDto medicalRecord) throws IOException {
        return repository.update(mapper.medicalRecordDtoToEntity(medicalRecord));
    }

    @Override
    public GenericResponseModel delete(String firstName, String lastName) throws IOException {
        final boolean isSuccessfullyDeleted = repository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseModel(true, String.format("The medical record for %s %s has been successfully deleted !", firstName, lastName));

        } else {
            return new GenericResponseModel(false, String.format("Medical record for %s %s not found", firstName, lastName));
        }
    }
}
