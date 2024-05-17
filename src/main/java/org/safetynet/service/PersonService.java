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
    List<PersonEntity> findAll() ;

    PersonEntity save(PersonEntity person) ;

    GenericResponseDto delete(String firstName, String lastName) ;

    PersonDto update(PersonEntity person);

    PersonExtendedDto findPerson(String firstName, String lastName) ;

    List<PersonExtendedDto> findPersons(String address);

    PersonsWithAgeRepartitionDto findPersonsCoveredByFireStation(int fireStationNumber);

    List<ChildDto> findChildrenByAddress(String address);

    TreeSet<String> findPersonsPhoneNumberByStation(int station) ;

    TreeSet<String> findPersonsEmail(String city);

}
