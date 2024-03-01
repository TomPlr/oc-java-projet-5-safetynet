package org.safetynet.service;

import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStationEntity> findAll() throws IOException;

    FireStationDto save(FireStationDto fireStation) throws IOException;

    FireStationDto update(FireStationDto fireStation) throws IOException;

    GenericResponseModel delete(FireStationDto fireStation) throws IOException;
}
