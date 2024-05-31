package org.safetynet.dto;

import java.util.List;

public record ChildDto(String firstName, String lastName, int age, List<PersonLiteDto> familyMembers) {
}
