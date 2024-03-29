package org.safetynet.model;

import org.safetynet.dto.PersonDto;

import java.util.List;

public record PersonsWithAgeRepartitionModel(List<PersonDto> Persons, int totalOver18, int totalUnderOrEqual18) {
}
