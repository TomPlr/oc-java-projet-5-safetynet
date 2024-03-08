package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    private ResponseEntity<List<PersonEntity>> getPersons() throws IOException {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<PersonEntity> createPerson(@Valid @RequestBody PersonEntity person) throws IOException {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping
    private ResponseEntity<GenericResponseModel> deletePerson(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
        return new ResponseEntity<>(personService.delete(firstName, lastName), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto person) throws IOException {
        return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
    }
}
