package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.ChildDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonWithoutAddressAndEmailDto;
import org.safetynet.dto.PersonWithoutPhoneDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.dto.MedicalHistoryDto;
import org.safetynet.dto.PersonExtendedDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonExtendedDto toPersonModel(PersonEntity personEntity, long age, MedicalHistoryDto medicalHistory, List<PersonDto> familyMembers);

    PersonDto toPersonDto(PersonEntity personEntity);

    PersonDto toPersonDto(PersonExtendedDto personExtendedDto);

    ChildDto toChildDto(PersonExtendedDto personExtendedDto);

    PersonWithoutAddressAndEmailDto toPersonWithoutAddressAndEmailDto(PersonExtendedDto personExtendedDto);

    PersonWithoutPhoneDto toPersonWithoutPhoneDto(PersonExtendedDto personExtendedDto);




}