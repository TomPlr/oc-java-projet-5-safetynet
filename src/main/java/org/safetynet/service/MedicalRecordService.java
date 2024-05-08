package org.safetynet.service;

import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.GenericResponseDto;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecordEntity> findAll() throws IOException;

    MedicalRecordEntity save(MedicalRecordEntity medicalRecord) throws IOException;

    MedicalRecordEntity update(MedicalRecordEntity medicalRecord) throws IOException;

    GenericResponseDto delete(String firstName, String lastName) throws IOException;
}
