package org.safetynet.controller;

import lombok.AllArgsConstructor;
import org.safetynet.entity.Person;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {
private final PersonService personService;

	@GetMapping("/person")
	private ResponseEntity<List<Person>> getPersons() throws IOException {
		return new ResponseEntity<>(personService.findAllPersons(),HttpStatus.OK);
	}


}
