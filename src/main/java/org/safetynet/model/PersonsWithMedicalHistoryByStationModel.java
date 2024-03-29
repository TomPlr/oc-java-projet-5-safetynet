package org.safetynet.model;

import java.util.List;

public record PersonsWithMedicalHistoryByStationModel(List<PersonWithMedicalHistoryModel> persons, int station) {
}
