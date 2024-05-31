package org.safetynet.repository;

import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonLiteDto;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.entity.PersonEntity;

import java.util.List;
import java.util.TreeSet;

public interface PersonRepository {

    /**
     * Get list of all persons
     *
     * @return List<PersonEntity>
     */
    List<PersonDto> findAll();

    /**
     * Create a new person
     *
     * @param personDto PersonDto
     */
    PersonDto save(PersonDto personDto);

    /**
     * Update a person
     *
     * @param personDto PersonDto
     */
    PersonLiteDto update(PersonDto personDto);

    /**
     * Delete a person
     *
     * @param firstName String
     * @param lastName  String
     */
    boolean delete(String firstName, String lastName);

    /**
     * Get a list of persons covered by a specific station
     *
     * @param fireStationNumber int
     */
    PersonsWithAgeRepartitionDto findPersonsByStationNumber(int fireStationNumber);

    /**
     * Get a list of person living at a specific address
     *
     * @param address String
     */
    List<PersonDto> findPersonsByAddress(String address);

    /**
     * Get a list of phone numbers by fire station
     *
     * @param addresses List<String>
     */
    TreeSet<String> findPersonsPhoneNumbersByAddresses(List<String> addresses);

    /**
     * Get persons
     *
     * @param firstName String
     * @param lastName  String
     */
    PersonDto findPersonByName(String firstName, String lastName);

    /**
     * Get persons' email by city
     *
     * @param city String
     */
    TreeSet<String> findEmailsByCity(String city);

}
