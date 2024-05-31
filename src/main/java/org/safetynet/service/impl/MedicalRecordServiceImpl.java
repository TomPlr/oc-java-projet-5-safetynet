package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository repository;

    @Override
    public List<MedicalRecordDto> findAll()  {
        return repository.findAll();
    }

    @Override
    public MedicalRecordDto save(MedicalRecordDto medicalRecord)  {
        return repository.save(medicalRecord);
    }

    @Override
    public MedicalRecordDto update(MedicalRecordDto medicalRecord)  {
        return repository.update(medicalRecord);
    }

    @Override
    public GenericResponseDto delete(String firstName, String lastName)  {
        final boolean isSuccessfullyDeleted = repository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseDto(true, String.format("The medical record for %s %s has been successfully deleted !", firstName, lastName));

        } else {
            return new GenericResponseDto(false, String.format("Medical record for %s %s not found", firstName, lastName));
        }
    }
}
