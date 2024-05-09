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

    @GetMapping("/*")
    public ResponseEntity<List<FireStationEntity>> getFireStations() throws IOException, NoSuchElementException {
        return new ResponseEntity<>(fireStationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FireStationEntity> createFireStation(@Valid @RequestBody FireStationEntity fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.save(fireStation), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FireStationEntity> updateFireStation(@Valid @RequestBody FireStationEntity fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.update(fireStation), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteFireStation(@RequestBody FireStationEntity fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.delete(fireStation), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PersonsWithAgeRepartitionDto> getPersonsCoveredByFireStation(@RequestParam("stationNumber") int stationNumber) throws IOException {
        return new ResponseEntity<>(personService.getPersonsCoveredByFireStation(stationNumber), HttpStatus.OK);
    }
}
