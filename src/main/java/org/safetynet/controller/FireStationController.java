package org.safetynet.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.FireStationDto;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/firestation")
@AllArgsConstructor
public class FireStationController {

    private final FireStationService fireStationService;
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<FireStationDto>> getFireStations() {
        log.info("Getting fire stations...");
        List<FireStationDto> findAll = fireStationService.findAll();
        log.info("Found {} fire stations.", findAll.size());
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FireStationDto> createFireStation(@Valid @RequestBody FireStationDto fireStation) {
        log.info("Creating new fire station...");
        FireStationDto fireStationDto = fireStationService.save(fireStation);
        log.info("Created new fire station: {}", fireStationDto);
        return new ResponseEntity<>(fireStationDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FireStationDto> updateFireStation(@Valid @RequestBody FireStationDto fireStation) {
        log.info("Updating fire station...");
        FireStationDto fireStationDto = fireStationService.update(fireStation);
        log.info("Updated new fire station: {}", fireStationDto);
        return new ResponseEntity<>(fireStationDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteFireStation(@RequestBody FireStationDto fireStation) {
        log.info("Deleting fire station...");
        GenericResponseDto genericResponseDto = fireStationService.delete(fireStation);
        log.info("{}", (genericResponseDto != null) ? genericResponseDto.details() : "no details");
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

    @GetMapping(params = "stationNumber")
    public ResponseEntity<PersonsWithAgeRepartitionDto> findPersonsCoveredByFireStation(@RequestParam("stationNumber") int stationNumber) {
        log.info("Looking for persons covered by fire station n°{}...", stationNumber);
        PersonsWithAgeRepartitionDto personsWithAgeRepartitionDto = personService.findPersonsCoveredByFireStation(stationNumber);
        log.info("Found {} persons", (personsWithAgeRepartitionDto != null ? personsWithAgeRepartitionDto.persons().size() : "0"));
        return new ResponseEntity<>(personsWithAgeRepartitionDto, HttpStatus.OK);
    }
}
