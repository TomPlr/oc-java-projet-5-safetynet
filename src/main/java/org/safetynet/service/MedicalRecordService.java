package org.safetynet.service;

import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.GenericResponseDto;

import java.io.IOException;
import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecordEntity> findAll();

    MedicalRecordEntity save(MedicalRecordEntity medicalRecord) ;

    MedicalRecordEntity update(MedicalRecordEntity medicalRecord) ;

    GenericResponseDto delete(String firstName, String lastName) ;
}
