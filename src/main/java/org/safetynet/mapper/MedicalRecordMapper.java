package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.MedicalHistoryDto;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    MedicalRecordEntity toMedicalRecordEntity(MedicalRecordDto dto);

    MedicalRecordDto toMedicalRecordDto(MedicalRecordEntity entity);

    MedicalHistoryDto toMedicalHistory(MedicalRecordDto medicalRecordEntity);
}
