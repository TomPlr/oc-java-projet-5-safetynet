package org.safetynet.repository;

import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;

import java.util.List;

public interface FireStationRepository {
    /**
     * Get list of all stations
     *
     * @return List<FireStationEntity>
     */
    List<FireStationEntity> findAll();

    /**
     * Create a new fire station
     *
     * @param fireStationEntity FireStationEntity
     */
    FireStationDto save(FireStationEntity fireStationEntity);

    /**
     * Update the station number of an address
     *
     * @param fireStationEntity FireStation
     */
    FireStationDto update(FireStationEntity fireStationEntity);

    /**
     * Delete a fire station
     *
     * @param fireStationEntity FireStationEntity
     */
    boolean delete(FireStationEntity fireStationEntity);
}