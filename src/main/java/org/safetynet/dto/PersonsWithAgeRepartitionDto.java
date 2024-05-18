package org.safetynet.dto;

import java.util.List;

public record PersonsWithAgeRepartitionDto(List<PersonDto> persons, int totalOver18, int totalUnderOrEqual18) {
}
