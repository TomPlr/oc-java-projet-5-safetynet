package org.safetynet.service;

import jakarta.validation.Valid;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.entity.FireStationEntity;

import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStationEntity> findAll() ;

    FireStationEntity save(@Valid FireStationEntity fireStation) ;

    FireStationEntity update(FireStationEntity fireStation) ;

    GenericResponseDto delete(FireStationEntity fireStation) ;

    /**
     * Get addresses by fire station
     *
     * @param station int
     */
    List<String> findAddressesByStation(int station);

    int findStation(String address);
}
