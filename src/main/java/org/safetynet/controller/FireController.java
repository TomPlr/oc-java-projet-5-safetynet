package org.safetynet.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/fire")
@AllArgsConstructor
public class FireController {

    private final PersonService personService;
    private final FireStationService fireStationService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<PersonsWithMedicalHistoryByStationDto> findPersonsWithMedicalHistory(@RequestParam String address)  {
        log.info("Looking for persons with medical history for this station address: {}...", address);
        List<PersonWithoutAddressAndEmailDto> persons = personService.findPersons(address)
                .stream()
                .map(personMapper::toPersonWithoutAddressAndEmailDto)
                .toList();
        log.info("Found {} persons", persons.size());
        log.info("Retrieving number for this station address: {}...", address);
        int station = fireStationService.findStation(address);
        log.info("Found station n°{}", station);

        return new ResponseEntity<>(new PersonsWithMedicalHistoryByStationDto(persons,station), HttpStatus.OK);
    }
}
