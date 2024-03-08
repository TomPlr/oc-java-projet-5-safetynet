package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.ChildModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName")
    })
    PersonDto personEntityToDto(PersonEntity personEntity);

    ChildModel toPersonWithAgeModel(PersonEntity person, int age, List<PersonDto> familyMembers);
}
