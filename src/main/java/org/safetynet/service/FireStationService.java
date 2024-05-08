package org.safetynet.service;

import jakarta.validation.Valid;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.dto.GenericResponseDto;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStationEntity> findAll() throws IOException;

    FireStationEntity save(@Valid FireStationEntity fireStation) throws IOException;

    FireStationEntity update(FireStationEntity fireStation) throws IOException;

    GenericResponseDto delete(FireStationEntity fireStation) throws IOException;

    int getStation(String address) throws IOException;

    String getStation(int station) throws IOException;

}
