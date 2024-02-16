package org.safetynet.repository;

import org.safetynet.domain.Person;
import org.safetynet.entity.PersonEntity;

import java.util.List;

public interface PersonRepository {

    /**
     * Get list of all persons
     * @return List<PersonEntity>
     */
    List<PersonEntity> findAll();

    /**
     * Create a new person
     * @param person PersonEntity
     */
    void save(PersonEntity person);

    /**
     * Delete a person
     * @param firstName String
     * @param lastName String
     */
    void delete(String firstName, String lastName);

    /**
     * Update a person
     * @param person Person
     */
    void update(Person person);

}
