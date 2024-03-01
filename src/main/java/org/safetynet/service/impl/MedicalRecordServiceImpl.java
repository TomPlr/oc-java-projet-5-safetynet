package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private MedicalRecordRepository repository;

    @Override
    public List<MedicalRecordEntity> findAll() throws IOException {
        return repository.findAll();
    }

    @Override
    public MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity) throws IOException {
        return repository.save(medicalRecordEntity);
    }

    @Override
    public MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity) throws IOException {
        return repository.update(medicalRecordEntity);
    }

    @Override
    public GenericResponseModel delete(String firstName, String lastName) throws IOException {
        repository.delete(firstName,lastName);
        return GenericResponseModel
                .builder()
                .success(true)
                .details(String.format("The medical record for %s %s has been successfully deleted !", firstName,lastName))
                .build();
    }
}
