package org.safetynet.service;

import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.GenericResponseModel;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecordEntity> findAll() throws IOException;

    MedicalRecordDto save(MedicalRecordDto medicalRecord) throws IOException;

    MedicalRecordDto update(MedicalRecordDto medicalRecord) throws IOException;

    GenericResponseModel delete(String firstName, String lastName) throws IOException;
}
