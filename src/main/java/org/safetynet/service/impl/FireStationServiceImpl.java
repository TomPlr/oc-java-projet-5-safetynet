package org.safetynet.service.impl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.service.FireStationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FireStationServiceImpl implements FireStationService {

    private FireStationRepository repository;

    @Override
    public List<FireStationDto> findAll()  {
        return repository.findAll();
    }

    @Override
    public FireStationDto save(@Valid FireStationDto fireStation)  {
        return repository.save(fireStation);
    }

    @Override
    public FireStationDto update(FireStationDto fireStation)  {
        return repository.update(fireStation);

    }

    @Override
    public GenericResponseDto delete(FireStationDto fireStation)  {
        final boolean isSuccessfullyDeleted = repository.delete(fireStation);

        if (isSuccessfullyDeleted) {
            return new GenericResponseDto(true, String
                    .format("Fire station n°%s will no longer operate from the following address: %s", fireStation.station(), fireStation.address()));

        } else {
            return new GenericResponseDto(false, String
                    .format("Error: Fire station n°%s does not operate from the following address: %s", fireStation.station(), fireStation.address()));

        }
    }

    @Override
    public List<String> findAddressesByStation(int station) {
        return repository.findAddressesByStation(station);
    }

    @Override
    public int findStation(String address) {
        return repository.findFireStation(address).station();
    }
}
