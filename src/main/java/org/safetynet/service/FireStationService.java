package org.safetynet.service;

import jakarta.validation.Valid;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.entity.FireStationEntity;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStationEntity> findAll() throws IOException;

    FireStationEntity save(@Valid FireStationEntity fireStation) throws IOException;

    FireStationEntity update(FireStationEntity fireStation) throws IOException;

    GenericResponseDto delete(FireStationEntity fireStation) throws IOException;

    /**
     * Get addresses by fire station
     *
     * @param station int
     */
    List<String> findAddressesByStation(int station);

    int findStation(String address) throws IOException;
}
