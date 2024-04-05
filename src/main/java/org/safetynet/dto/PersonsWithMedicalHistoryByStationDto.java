package org.safetynet.dto;

import java.util.List;

public record PersonsWithMedicalHistoryByStationDto(List<PersonWithoutAddressAndEmailDto> persons, int station) {
}
