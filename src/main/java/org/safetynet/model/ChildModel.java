package org.safetynet.model;

import org.safetynet.dto.PersonDto;

import java.util.List;

public record ChildModel(String firstName, String lastName, int age, List<PersonDto> familyMembers) {
}
