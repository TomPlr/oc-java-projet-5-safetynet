package org.safetynet.repository.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.JsonDataModel;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataLoadJson {
    protected static final List<PersonEntity> PERSON_ENTITIES = new ArrayList<>();
    protected static final List<FireStationEntity> FIRE_STATION_ENTITIES = new ArrayList<>();
    protected static final List<MedicalRecordEntity> MEDICAL_RECORDS_ENTITIES = new ArrayList<>();
    private static String jsonPath;
    static ObjectMapper objectMapper = new ObjectMapper();

    public DataLoadJson(Environment env) {
        jsonPath = env.getProperty("data.json.resources");
    }

    public static void init() {
        try {
            final var json = objectMapper.readValue(new File(jsonPath), JsonDataModel.class);

            PERSON_ENTITIES.clear();
            FIRE_STATION_ENTITIES.clear();
            MEDICAL_RECORDS_ENTITIES.clear();

            PERSON_ENTITIES.addAll(json.persons());
            FIRE_STATION_ENTITIES.addAll(json.firestations());
            MEDICAL_RECORDS_ENTITIES.addAll(json.medicalrecords());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
