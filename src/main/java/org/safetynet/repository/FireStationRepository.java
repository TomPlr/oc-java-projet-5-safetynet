package org.safetynet.repository;

import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;

import java.util.List;

public interface FireStationRepository {
    /**
     * Get list of all stations
     *
     * @return List<FireStationDto>
     */
    List<FireStationDto> findAll();

    /**
     * Create a new fire station
     *
     * @param fireStationDto FireStationDto
     */
    FireStationDto save(FireStationDto fireStationDto);

    /**
     * Update the station number of an address
     *
     * @param fireStationDto FireStation
     */
    FireStationDto update(FireStationDto fireStationDto);

    /**
     * Delete a fire station
     *
     * @param fireStationDto FireStationEntity
     */
    boolean delete(FireStationDto fireStationDto);

    /**
     * Get addresses by fire station
     *
     * @param station int
     */
    List<String> findAddressesByStation(int station);

    /**
     * Get fire station by address
     *
     * @param address String
     */
    FireStationDto findFireStation(String address);

}
