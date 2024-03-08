package org.safetynet.service.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.mapper.FireStationMapper;
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
    private FireStationMapper mapper;

    @Override
    public List<FireStationEntity> findAll() throws IOException {
        return repository.findAll();
    }

    @Override
    public FireStationDto save(FireStationDto fireStation) throws IOException {
        return repository.save(mapper.fireStationDtoToEntity(fireStation));
    }

    @Override
    public FireStationDto update(FireStationDto fireStation) throws IOException {
        return repository.update(mapper.fireStationDtoToEntity(fireStation));

    }

    @Override
    public GenericResponseModel delete(FireStationDto fireStation) throws IOException {
        final boolean isSuccessfullyDeleted = repository.delete(mapper.fireStationDtoToEntity(fireStation));

        if (isSuccessfullyDeleted) {
            return GenericResponseModel
                    .builder()
                    .success(true)
                    .details(String
                            .format("Fire station n°%s will no longer operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()))
                    .build();
        } else {
            return GenericResponseModel
                    .builder()
                    .success(false)
                    .details(String
                            .format("Error: Fire station n°%s does not operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()))
                    .build();

        }

    }


}
