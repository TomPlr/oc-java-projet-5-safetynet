package org.safetynet.controller;


import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonWithoutAddressAndEmailDto;
import org.safetynet.dto.PersonsWithMedicalHistoryByStationDto;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fire")
@AllArgsConstructor
public class FireController {

    private final PersonService personService;
    private final FireStationService fireStationService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<PersonsWithMedicalHistoryByStationDto> getPersonsWithMedicalHistory(@RequestParam String address) throws IOException {
        List<PersonWithoutAddressAndEmailDto> persons = personService.getPersons(address)
                .stream()
                .map(personMapper::toPersonWithoutAddressAndEmailDto)
                .toList();

        return new ResponseEntity<>(new PersonsWithMedicalHistoryByStationDto(persons, fireStationService.getStation(address)), HttpStatus.OK);
    }
}
