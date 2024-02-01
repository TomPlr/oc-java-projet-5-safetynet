package org.safetynet.service;

import org.safetynet.entity.Person;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public interface PersonService {
	List<Person> findAllPersons() throws IOException;
}
