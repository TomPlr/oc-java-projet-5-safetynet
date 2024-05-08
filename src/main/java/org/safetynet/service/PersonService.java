package org.safetynet.service;

import org.safetynet.dto.ChildDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonExtendedDto;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public interface PersonService {
    List<PersonEntity> findAll() throws IOException;

    PersonEntity save(PersonEntity person) throws IOException;

    GenericResponseDto delete(String firstName, String lastName) throws IOException;

    PersonDto update(PersonEntity person) throws IOException;

    PersonExtendedDto getPerson(String firstName, String lastName) throws IOException;

    List<PersonExtendedDto> getPersons(String address) throws IOException;

    PersonsWithAgeRepartitionDto getPersonsCoveredByFireStation(int fireStationNumber) throws IOException;

    List<ChildDto> getChildrenByAddress(String address) throws IOException;

    TreeSet<String> getPersonsPhoneNumberByStation(int station) throws IOException;

    TreeSet<String> getPersonsEmail(String city) throws IOException;

}
