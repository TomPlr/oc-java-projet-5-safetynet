package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonWithoutPhoneDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.dto.ChildDto;
import org.safetynet.model.MedicalHistoryModel;
import org.safetynet.dto.PersonWithoutAddressAndEmailDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toPersonDto(PersonEntity personEntity);

    ChildDto toPersonWithAgeDto(PersonEntity person, int age, List<PersonDto> familyMembers);

    PersonWithoutAddressAndEmailDto toPersonWithoutAddressAndEmailDto(PersonEntity person, long age, MedicalHistoryModel medicalHistory);

    PersonWithoutPhoneDto toPersonWithoutPhoneDto(PersonEntity person, long age, MedicalHistoryModel medicalHistory);
}