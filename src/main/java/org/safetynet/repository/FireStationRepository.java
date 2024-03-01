package org.safetynet.repository;

import org.safetynet.domain.FireStation;
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
    FireStationEntity save(FireStationEntity fireStationEntity);

    /**
     * Update the station number of an address
     *
     * @param fireStation FireStation
     */
    FireStationEntity update(FireStation fireStation);

    /**
     * Delete a fire station
     *
     * @param fireStationEntity FireStationEntity
     */
    void delete(FireStationEntity fireStationEntity);
}
