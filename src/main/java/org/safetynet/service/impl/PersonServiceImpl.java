package org.safetynet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.safetynet.entity.Data;
import org.safetynet.entity.Person;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

@Override
public List<Person> findAllPersons() throws IOException {
	ObjectMapper objectMapper = new ObjectMapper();
	Data data = objectMapper.readValue(new URL("file:src/main/resources/data.json"), Data.class);
	return null;
};
}
