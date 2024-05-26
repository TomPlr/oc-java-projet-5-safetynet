package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.mapper.FireStationMapper;
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

FireStationMapper fireStationMapper;

    @Override
    public List<FireStationDto> findAll() {
        return FIRE_STATION_ENTITIES.stream().map(fireStationEntity -> fireStationMapper.toFireStationDto(fireStationEntity)).toList();
    }

    @Override
    public FireStationDto save(FireStationDto fireStationDto) throws NoSuchElementException {
        FireStationEntity fireStationEntity = fireStationMapper.toFireStationEntity(fireStationDto);

        boolean isPresent = FIRE_STATION_ENTITIES.stream().anyMatch(fireStation -> fireStation.getAddress().equalsIgnoreCase(fireStationEntity.getAddress()) && fireStation.getStation() == fireStationEntity.getStation());
        if (!isPresent) {
            FIRE_STATION_ENTITIES.add(fireStationEntity);
        } else {
            throw new NoSuchElementException("Fire station already exists");
        }

        Optional<FireStationDto> optionalFireStation = FIRE_STATION_ENTITIES
                .stream()
                .filter(fireStationEntity::equals)
                .map(fireStation -> fireStationMapper.toFireStationDto(fireStation))
                .findFirst();

        return optionalFireStation.orElseThrow(() -> new NoSuchElementException("Fire station has not been saved."));
    }

    @Override
    public FireStationDto update(FireStationDto fireStationDto) throws NoSuchElementException {

        return FIRE_STATION_ENTITIES.stream()
                .filter(fireStation -> fireStation.getAddress().equals(fireStationDto.address()))
                .findFirst()
                .map(fs -> {
                    fs.setStation(fireStationDto.station());
                    return fireStationMapper.toFireStationDto(fs);
                })
                .orElseThrow(() -> new NoSuchElementException("No fire station have been found at this address : " + fireStationDto.address()));
    }

    @Override
    public boolean delete(FireStationDto fireStationDto) throws NoSuchElementException {
        return FIRE_STATION_ENTITIES
                .removeIf(fireStation ->
                        fireStation.getAddress().equals(fireStationDto.address()) && fireStation.getStation() == fireStationDto.station());
    }

    @Override
    public List<String> findAddressesByStation(int station) {
        List<String> addresses = new ArrayList<>();
        FIRE_STATION_ENTITIES.stream()
                .filter(fireStation ->
                    fireStation.getStation() == station
                )
                .forEach(fireStation -> addresses.add(fireStation.getAddress()));

        return addresses;
    }

    @Override
    public FireStationDto findFireStation(String address) {
        return FIRE_STATION_ENTITIES.stream()
                .filter(fireStationEntity -> fireStationEntity.getAddress().equalsIgnoreCase(address))
                .map(fireStationEntity -> fireStationMapper.toFireStationDto(fireStationEntity))
                .findFirst()
                .orElse(null);
    }
}
