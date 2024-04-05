package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.MedicalHistoryModel;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalRecordDto medicalRecordEntityToDto(MedicalRecordEntity medicalRecordEntity);

    MedicalRecordEntity medicalRecordDtoToEntity(MedicalRecordDto medicalRecordDto);

    MedicalHistoryModel toMedicalHistory(MedicalRecordEntity medicalRecordEntity);
}
