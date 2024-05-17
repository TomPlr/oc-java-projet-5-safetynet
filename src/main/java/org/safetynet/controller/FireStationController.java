package org.safetynet.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/firestation")
@AllArgsConstructor
public class FireStationController {

    private final FireStationService fireStationService;
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<FireStationEntity>> getFireStations()  {
        return new ResponseEntity<>(fireStationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FireStationEntity> createFireStation(@Valid @RequestBody FireStationEntity fireStation)  {
        return new ResponseEntity<>(fireStationService.save(fireStation), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FireStationEntity> updateFireStation(@Valid @RequestBody FireStationEntity fireStation) {
        return new ResponseEntity<>(fireStationService.update(fireStation), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteFireStation(@RequestBody FireStationEntity fireStation)  {
        return new ResponseEntity<>(fireStationService.delete(fireStation), HttpStatus.OK);
    }

    @GetMapping(params = "stationNumber")
    public ResponseEntity<PersonsWithAgeRepartitionDto> findPersonsCoveredByFireStation(@RequestParam("stationNumber") int stationNumber)  {
        return new ResponseEntity<>(personService.findPersonsCoveredByFireStation(stationNumber), HttpStatus.OK);
    }
}
