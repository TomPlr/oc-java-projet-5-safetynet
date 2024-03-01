package org.safetynet.service;

import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.GenericResponseModel;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    List<PersonEntity> findAll() throws IOException;

    PersonDto save(PersonDto person) throws IOException;

    PersonDto update(PersonDto person) throws IOException;

    GenericResponseModel delete(String firstName, String lastName) throws IOException;
}
