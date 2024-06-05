package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonLiteDto;
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
    public ResponseEntity<List<PersonDto>> getPersons() {
        log.info("Getting all persons...");
        List<PersonDto> personDtos = personService.findAll();
        log.info("Found {} persons.", personDtos.size());
        return new ResponseEntity<>(personDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto person) {
        log.info("Creating a new person...");
        PersonDto personDto = personService.save(person);
        log.info("New person created!");
        return new ResponseEntity<>(personDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Deleting a person...");
        GenericResponseDto genericResponseDto = personService.delete(firstName, lastName);
        log.info("{}", (genericResponseDto != null) ? genericResponseDto.details() : "no details");
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonLiteDto> updatePerson(@RequestBody PersonDto person) {
        log.info("Updating a person...");
        PersonLiteDto personLiteDto = personService.update(person);
        log.info("Person updated!");
        return new ResponseEntity<>(personLiteDto, HttpStatus.OK);
    }
}
