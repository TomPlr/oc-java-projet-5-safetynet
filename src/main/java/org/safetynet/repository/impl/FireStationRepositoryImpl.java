package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.domain.FireStation;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Repository;

import static org.safetynet.repository.impl.DataLoadJson.FIRESTATION_ENTITIES;

import java.util.List;
import java.util.Optional;

@Repository
public class FireStationRepositoryImpl implements FireStationRepository {

    @Override
    public List<FireStationEntity> findAll() {
        return FIRESTATION_ENTITIES ;
    }

    @Override
    public FireStationEntity save(FireStationEntity fireStationEntity) {
        FIRESTATION_ENTITIES.add(fireStationEntity);

        Optional<FireStationEntity> optionalFireStationEntity = FIRESTATION_ENTITIES
                .stream()
                .filter(fireStation -> fireStation.equals(fireStationEntity))
                .findFirst();

        return optionalFireStationEntity.orElseThrow(() -> new RuntimeException("FireStationEntity not found"));
    };

    @Override
    public FireStationEntity update(FireStation fireStation) {
        Optional<FireStationEntity> optionalFireStationEntity = FIRESTATION_ENTITIES
                .stream()
                .filter(fireStationEntity -> fireStation.getAddress().equals(fireStationEntity.getAddress()))
                .findFirst();

        return optionalFireStationEntity
                .map(fireStationEntity -> {
                    fireStationEntity.setStation(fireStation.getStation());
                    return fireStationEntity;})
                .orElseThrow(() -> new RuntimeException("FireStationEntity not found"));
    }

    @Override
    public void delete(FireStationEntity fireStation) {
        FIRESTATION_ENTITIES
                .stream()
                .filter(fireStationEntity -> fireStation.getAddress().equals(fireStationEntity.getAddress())  || fireStation.getStation() == fireStationEntity.getStation() )
                .findFirst().ifPresentOrElse(FIRESTATION_ENTITIES::remove,
                        () -> {
                            throw new RuntimeException("FireStationEntity not found");
                        });
    }
}
