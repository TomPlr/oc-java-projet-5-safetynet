package org.safetynet.repository;

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
     * @param fireStationEntity FireStation
     */
    FireStationEntity update(FireStationEntity fireStationEntity);

    /**
     * Delete a fire station
     *
     * @param fireStationEntity FireStationEntity
     */
    boolean delete(FireStationEntity fireStationEntity);

    /**
     * Get addresses by fire station
     *
     * @param station int
     */
    List<String> getAddressesByStation(int station);

    /**
     * Get fire station by address
     *
     * @param address String
     */
    FireStationEntity getFireStation(String address);

    /**
     * Get fire station by station
     *
     * @param station int
     */
    FireStationEntity getFireStation(int station);
}
