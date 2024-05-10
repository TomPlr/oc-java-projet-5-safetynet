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

    PersonExtendedDto findPerson(String firstName, String lastName) throws IOException;

    List<PersonExtendedDto> findPersons(String address) throws IOException;

    PersonsWithAgeRepartitionDto findPersonsCoveredByFireStation(int fireStationNumber) throws IOException;

    List<ChildDto> findChildrenByAddress(String address) throws IOException;

    TreeSet<String> findPersonsPhoneNumberByStation(int station) throws IOException;

    TreeSet<String> findPersonsEmail(String city) throws IOException;

}
