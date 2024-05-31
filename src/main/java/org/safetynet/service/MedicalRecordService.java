package org.safetynet.service;

import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.dto.GenericResponseDto;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecordDto> findAll();

    MedicalRecordDto save(MedicalRecordDto medicalRecord) ;

    MedicalRecordDto update(MedicalRecordDto medicalRecord) ;

    GenericResponseDto delete(String firstName, String lastName) ;
}
