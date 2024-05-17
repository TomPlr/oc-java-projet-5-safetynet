package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<List<PersonEntity>> getPersons()  {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@Valid @RequestBody PersonEntity person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deletePerson(@RequestParam String firstName, @RequestParam String lastName)  {
        return new ResponseEntity<>(personService.delete(firstName, lastName), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonEntity person)  {
        return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
    }
}
