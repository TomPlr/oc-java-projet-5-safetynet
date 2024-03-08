package org.safetynet.repository;

import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.ChildModel;
import org.safetynet.model.PersonsByStationModel;

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
    PersonsByStationModel findPersonsByStationNumber(int fireStationNumber);

    /**
     * Get a list of children living at a specific address
     *
     * @param address String
     */
    List<ChildModel> findPersonsByAddress(String address);

}
