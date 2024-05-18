package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<List<PersonEntity>> getPersons() {
        log.info("Getting all persons...");
        List<PersonEntity> personEntities = personService.findAll();
        log.info("Found {} persons.", personEntities.size());
        return new ResponseEntity<>(personEntities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@Valid @RequestBody PersonEntity person) {
        log.info("Creating a new person...");
        PersonEntity personEntity = personService.save(person);
        log.info("New person created!");
        return new ResponseEntity<>(personEntity, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Deleting a person...");
        GenericResponseDto genericResponseDto = personService.delete(firstName, lastName);
        log.info("{}", (genericResponseDto != null) ? genericResponseDto.details() : "no details");
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonEntity person) {
        log.info("Updating a person...");
        PersonDto personDto = personService.update(person);
        log.info("Person updated!");
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }
}
