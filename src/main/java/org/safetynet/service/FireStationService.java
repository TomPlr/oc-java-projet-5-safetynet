package org.safetynet.service;

import jakarta.validation.Valid;
import org.safetynet.dto.FireStationDto;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.entity.FireStationEntity;

import java.util.List;

public interface FireStationService {
    List<FireStationDto> findAll() ;

    FireStationDto save(@Valid FireStationDto fireStation) ;

    FireStationDto update(FireStationDto fireStation) ;

    GenericResponseDto delete(FireStationDto fireStation) ;

    /**
     * Get addresses by fire station
     *
     * @param station int
     */
    List<String> findAddressesByStation(int station);

    int findStation(String address);
}
