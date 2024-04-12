package org.safetynet.service.impl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.service.FireStationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FireStationServiceImpl implements FireStationService {

    private FireStationRepository repository;

    @Override
    public List<FireStationEntity> findAll() throws IOException {
        return repository.findAll();
    }

    @Override
    public FireStationEntity save(@Valid FireStationEntity fireStation) throws IOException {
        return repository.save(fireStation);
    }

    @Override
    public FireStationEntity update(FireStationEntity fireStation) throws IOException {
        return repository.update(fireStation);

    }

    @Override
    public GenericResponseModel delete(FireStationEntity fireStation) throws IOException {
        final boolean isSuccessfullyDeleted = repository.delete(fireStation);

        if (isSuccessfullyDeleted) {
            return new GenericResponseModel(true, String
                    .format("Fire station n°%s will no longer operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()));

        } else {
            return new GenericResponseModel(false, String
                    .format("Error: Fire station n°%s does not operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()));

        }
    }

    @Override
    public int getStation(String address) throws IOException {
        return repository.getFireStation(address).getStation();
    }

    @Override
    public String getStation(int station) throws IOException {
        return repository.getFireStation(station).getAddress();
    }
}
