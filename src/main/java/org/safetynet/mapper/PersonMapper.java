package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.ChildDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonWithoutAddressAndEmailDto;
import org.safetynet.dto.PersonWithoutPhoneDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.MedicalHistoryModel;
import org.safetynet.model.PersonModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonModel toPersonModel(PersonEntity personEntity, long age, MedicalHistoryModel medicalHistory, List<PersonDto> familyMembers);

    PersonDto toPersonDto(PersonEntity personEntity);

    PersonDto toPersonDto(PersonModel personModel);

    ChildDto toChildDto(PersonModel personModel);

    PersonWithoutAddressAndEmailDto toPersonWithoutAddressAndEmailDto(PersonModel personModel);

    PersonWithoutPhoneDto toPersonWithoutPhoneDto(PersonModel personModel);




}