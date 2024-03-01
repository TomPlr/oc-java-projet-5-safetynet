package org.safetynet.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.service.FireStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class FireStationController {

    private final FireStationService fireStationService;

    @GetMapping("/firestation")
    private ResponseEntity<List<FireStationEntity>> getFireStations() throws IOException, NoSuchElementException {
        return new ResponseEntity<>(fireStationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/firestation")
    private ResponseEntity<FireStationDto> createFireStation(@Valid @RequestBody FireStationDto fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.save(fireStation), HttpStatus.CREATED);
    }

    @PutMapping("/firestation")
    private ResponseEntity<FireStationDto> updateFireStation(@Valid @RequestBody FireStationDto fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.update(fireStation), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/firestation")
    private ResponseEntity<GenericResponseModel> deleteFireStation(@RequestBody FireStationDto fireStation) throws IOException {
        return new ResponseEntity<>(fireStationService.delete(fireStation), HttpStatus.OK);
    }
}
