package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;

@Mapper(componentModel = "spring")
public interface FireStationMapper {

    FireStationDto toFireStationDto(FireStationEntity fireStationEntity);
    FireStationEntity toFireStationEntity(FireStationDto fireStationDto);

}
