package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto personEntityToDto(PersonEntity personEntity);
    PersonEntity PersonDtoToEntity(PersonDto personDto);
}
