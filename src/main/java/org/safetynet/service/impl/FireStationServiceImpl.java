package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.domain.FireStation;
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
    public FireStationEntity save(FireStationEntity fireStation) throws IOException {
        repository.save(fireStation);
        return fireStation;
    }

    @Override
    public FireStation update(FireStation fireStation) throws IOException {
        repository.update(fireStation);
        return fireStation;
    }

    @Override
    public GenericResponseModel delete(FireStationEntity fireStation) throws IOException {
        repository.delete(fireStation);
        return GenericResponseModel.builder().success(true).details(String.format("Fire station n°%s will no longer operate from the following address: %s", fireStation.getStation(), fireStation.getAddress())).build();
    }

}
