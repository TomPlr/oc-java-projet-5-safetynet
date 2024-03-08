package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.mapper.FireStationMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.*;

@Repository
@AllArgsConstructor
public class FireStationRepositoryImpl implements FireStationRepository {

    private final FireStationMapper mapper;
    private final PersonMapper personMapper;

    @Override
    public List<FireStationEntity> findAll() {
        return FIRE_STATION_ENTITIES;
    }

    @Override
    public FireStationDto save(FireStationEntity fireStationEntity) throws NoSuchElementException {
        FIRE_STATION_ENTITIES.add(fireStationEntity);

        Optional<FireStationDto> optionalFireStation = FIRE_STATION_ENTITIES
                .stream()
                .filter(fireStation -> fireStation.equals(fireStationEntity))
                .findFirst()
                .map(mapper::fireStationEntityToDto);

        return optionalFireStation.orElseThrow(() -> new NoSuchElementException("FireStationEntity not found"));
    }

    @Override
    public FireStationDto update(FireStationEntity fireStationEntity) throws NoSuchElementException {

        Optional<FireStationEntity> optionalFireStationEntity = FIRE_STATION_ENTITIES
                .stream()
                .filter(fireStation -> fireStation.getAddress().equals(fireStationEntity.getAddress()))
                .findFirst();

        return optionalFireStationEntity
                .map(fireStation -> {
                    fireStation.setStation(fireStationEntity.getStation());

                    return mapper.fireStationEntityToDto(fireStationEntity);
                })
                .orElseThrow(() -> new NoSuchElementException("FireStationEntity not found"));
    }

    @Override
    public boolean delete(FireStationEntity fireStationEntity) throws NoSuchElementException {
        return FIRE_STATION_ENTITIES
                .removeIf(fireStation ->
                        fireStation.getAddress().equals(fireStationEntity.getAddress()) && fireStation.getStation() == fireStationEntity.getStation());
    }


}
