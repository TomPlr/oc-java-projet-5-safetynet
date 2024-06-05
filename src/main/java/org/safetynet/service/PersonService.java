package org.safetynet.service;

import org.safetynet.dto.*;
import org.safetynet.entity.PersonEntity;

import java.util.List;
import java.util.TreeSet;

public interface PersonService {
    List<PersonDto> findAll() ;

    PersonDto save(PersonDto person) ;

    GenericResponseDto delete(String firstName, String lastName) ;

    PersonLiteDto update(PersonDto person);

    PersonExtendedDto findPerson(String firstName, String lastName) ;

    List<PersonExtendedDto> findPersons(String address);

    PersonsWithAgeRepartitionDto findPersonsCoveredByFireStation(int fireStationNumber);

    List<ChildDto> findChildrenByAddress(String address);

    TreeSet<String> findPersonsPhoneNumberByStation(int station) ;

    TreeSet<String> findPersonsEmail(String city);

}
