package org.safetynet.service;

import jakarta.validation.Valid;
import org.safetynet.dto.FireStationDto;
import org.safetynet.dto.GenericResponseDto;

import java.util.List;

public interface FireStationService {
    /**
     * Get all fire stations
     *
     * @return List<FireStationDto>
     */
    List<FireStationDto> findAll();

    /**
     * Save a new fire station
     *
     * @param fireStation FireStationDto
     * @return FireStationDto
     */
    FireStationDto save(@Valid FireStationDto fireStation);

    /**
     * Update a fire station
     *
     * @param fireStation FireStationDto
     * @return FireStationDto
     */
    FireStationDto update(FireStationDto fireStation);

    /**
     * Delete a fire station
     *
     * @param fireStation FireStationDto
     * @return GenericResponseDto
     */
    GenericResponseDto delete(FireStationDto fireStation);

    /**
     * Find all addresses covered by fire station
     *
     * @param station int
     * @return List<String>
     */
    List<String> findAddressesByStation(int station);

    /**
     * Find a station number by its address
     *
     * @param address String
     * @return int
     */
    int findStation(String address);
}
