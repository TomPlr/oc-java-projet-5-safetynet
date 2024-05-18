package org.safetynet.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.entity.FireStationEntity;
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
    public ResponseEntity<List<FireStationEntity>> getFireStations() {
        log.info("Getting fire stations...");
        List<FireStationEntity> findAll = fireStationService.findAll();
        log.info("Found {} fire stations.", findAll.size());
        return new ResponseEntity<>(findAll, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FireStationEntity> createFireStation(@Valid @RequestBody FireStationEntity fireStation) {
        log.info("Creating new fire station...");
        FireStationEntity fireStationEntity = fireStationService.save(fireStation);
        log.info("Created new fire station: {}", fireStationEntity);
        return new ResponseEntity<>(fireStationEntity, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FireStationEntity> updateFireStation(@Valid @RequestBody FireStationEntity fireStation) {
        log.info("Updating fire station...");
        FireStationEntity fireStationEntity = fireStationService.save(fireStation);
        log.info("Updated new fire station: {}", fireStationEntity);
        return new ResponseEntity<>(fireStationEntity, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteFireStation(@RequestBody FireStationEntity fireStation) {
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
