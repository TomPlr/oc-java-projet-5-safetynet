package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    MedicalRecordDto medicalRecordEntityToDto(MedicalRecordEntity medicalRecordEntity);

    MedicalRecordEntity medicalRecordDtoToEntity(MedicalRecordDto medicalRecordDto);

}
