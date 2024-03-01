package org.safetynet.repository;

import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.GenericResponseModel;

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
    PersonDto save(PersonEntity personEntity);

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

}
