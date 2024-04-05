package org.safetynet.mapper;

import org.mapstruct.Mapper;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;

@Mapper(componentModel = "spring")
public interface FireStationMapper {

    FireStationDto fireStationEntityToDto(FireStationEntity fireStationEntity);

    FireStationEntity fireStationDtoToEntity(FireStationDto fireStation);

}
