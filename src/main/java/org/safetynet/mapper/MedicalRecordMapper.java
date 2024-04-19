package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.MedicalHistoryModel;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalHistoryModel toMedicalHistory(MedicalRecordEntity medicalRecordEntity);
}
