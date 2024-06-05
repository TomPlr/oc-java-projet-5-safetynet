package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.*;
import org.safetynet.entity.PersonEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonEntity toPersonEntity (PersonDto personDto);

    PersonExtendedDto toPersonExtendedDto(PersonDto personDto, long age, MedicalHistoryDto medicalHistory, List<PersonLiteDto> familyMembers);

    PersonLiteDto toPersonLiteDto(PersonEntity personEntity);

    PersonLiteDto toPersonLiteDto(PersonExtendedDto personExtendedDto);

    PersonDto toPersonDto(PersonEntity personEntity);

    ChildDto toChildDto(PersonExtendedDto personExtendedDto);

    PersonWithoutAddressAndEmailDto toPersonWithoutAddressAndEmailDto(PersonExtendedDto personExtendedDto);

    PersonWithoutPhoneDto toPersonWithoutPhoneDto(PersonExtendedDto personExtendedDto);
}