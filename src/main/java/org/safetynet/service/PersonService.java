package org.safetynet.service;

import org.safetynet.domain.Person;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.GenericResponseModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface PersonService {
	List<PersonEntity> findAll() throws IOException;
	PersonEntity save(PersonEntity person) throws IOException;
	GenericResponseModel delete(String firstName, String lastName) throws IOException;
	Person update(Person person)throws IOException;
}
