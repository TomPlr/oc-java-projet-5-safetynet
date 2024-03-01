package org.safetynet.service;

import org.safetynet.domain.FireStation;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;


import java.io.IOException;
import java.util.List;

public interface FireStationService {
    List<FireStationEntity> findAll() throws IOException;
    FireStationEntity save(FireStationEntity fireStation) throws IOException;
    FireStation update(FireStation fireStation)throws IOException;
    GenericResponseModel delete(FireStationEntity fireStation)throws IOException;
}
