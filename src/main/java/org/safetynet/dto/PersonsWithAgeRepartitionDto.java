package org.safetynet.dto;

import java.util.List;

public record PersonsWithAgeRepartitionDto(List<PersonDto> Persons, int totalOver18, int totalUnderOrEqual18) {
}
