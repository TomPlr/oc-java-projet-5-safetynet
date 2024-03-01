package org.safetynet.service;

import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.GenericResponseModel;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecordEntity> findAll() throws IOException;
    MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity) throws IOException;
    GenericResponseModel delete(String firstName, String lastName) throws IOException;
    MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity)throws IOException;
}
