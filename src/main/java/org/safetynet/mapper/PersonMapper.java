package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.ChildModel;
import org.safetynet.model.MedicalHistoryModel;
import org.safetynet.model.PersonWithMedicalHistoryModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto personEntityToDto(PersonEntity personEntity);

    ChildModel toPersonWithAgeModel(PersonEntity person, int age, List<PersonDto> familyMembers);

    PersonWithMedicalHistoryModel toPersonWithMedicalRecordModel(PersonEntity person, long age, MedicalHistoryModel medicalHistory);
}