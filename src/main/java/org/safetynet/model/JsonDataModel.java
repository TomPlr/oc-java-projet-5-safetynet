package org.safetynet.model;

import org.safetynet.entity.FireStationEntity;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;

import java.util.List;

public record JsonDataModel(
        List<PersonEntity> persons,
        List<FireStationEntity> firestations,
        List<MedicalRecordEntity> medicalrecords) {
}
