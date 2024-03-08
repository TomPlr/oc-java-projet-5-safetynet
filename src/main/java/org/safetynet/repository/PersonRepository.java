package org.safetynet.repository;

import org.safetynet.domain.Persons;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;

import java.util.List;

public interface PersonRepository {

    /**
     * Get list of all persons
     *
     * @return List<PersonEntity>
     */
    List<PersonEntity> findAll();

    /**
     * Create a new person
     *
     * @param personEntity PersonEntity
     */
    PersonEntity save(PersonEntity personEntity);

    /**
     * Update a person
     *
     * @param personEntity PersonEntity
     */
    PersonDto update(PersonEntity personEntity);

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
    Persons findPersonsByStationNumber(int fireStationNumber);
}
