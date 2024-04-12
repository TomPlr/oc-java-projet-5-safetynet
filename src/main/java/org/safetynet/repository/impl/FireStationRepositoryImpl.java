package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.*;

@Repository
@AllArgsConstructor
public class FireStationRepositoryImpl implements FireStationRepository {

    @Override
    public List<FireStationEntity> findAll() {
        return FIRE_STATION_ENTITIES;
    }

    @Override
    public FireStationEntity save(FireStationEntity fireStationEntity) throws NoSuchElementException {
        FIRE_STATION_ENTITIES.add(fireStationEntity);

        Optional<FireStationEntity> optionalFireStation = FIRE_STATION_ENTITIES
                .stream()
                .filter(fireStation -> fireStation.equals(fireStationEntity))
                .findFirst();

        return optionalFireStation.orElseThrow(() -> new NoSuchElementException("FireStationEntity not found"));
    }

    @Override
    public FireStationEntity update(FireStationEntity fireStationEntity) throws NoSuchElementException {

        Optional<FireStationEntity> optionalFireStationEntity = FIRE_STATION_ENTITIES
                .stream()
                .filter(fireStation -> fireStation.getAddress().equals(fireStationEntity.getAddress()))
                .findFirst();

        return optionalFireStationEntity
                .orElseThrow(() -> new NoSuchElementException("FireStationEntity not found"));
    }

    @Override
    public boolean delete(FireStationEntity fireStationEntity) throws NoSuchElementException {
        return FIRE_STATION_ENTITIES
                .removeIf(fireStation ->
                        fireStation.getAddress().equals(fireStationEntity.getAddress()) && fireStation.getStation() == fireStationEntity.getStation());
    }

    @Override
    public List<String> getAddressesByStation(int station) {
        List<String> addresses = new ArrayList<>();
        FIRE_STATION_ENTITIES.stream()
                .filter(fireStation ->
                    fireStation.getStation() == station
                )
                .forEach(fireStation -> addresses.add(fireStation.getAddress()));

        return addresses;
    }

    @Override
    public FireStationEntity getFireStation(String address) {
        return FIRE_STATION_ENTITIES.stream()
                .filter(fireStationEntity -> fireStationEntity.getAddress().equalsIgnoreCase(address))
                .findFirst()
                .orElse(null);
    }

    @Override
    public FireStationEntity getFireStation(int station) {
        return FIRE_STATION_ENTITIES.stream()
                .filter(fireStationEntity -> fireStationEntity.getStation() == station)
                .findFirst()
                .orElse(null);
    }
}
