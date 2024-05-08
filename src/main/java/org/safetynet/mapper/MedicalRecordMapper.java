package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.MedicalHistoryDto;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalHistoryDto toMedicalHistory(MedicalRecordEntity medicalRecordEntity);
}
