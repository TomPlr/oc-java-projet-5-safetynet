package org.safetynet.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.domain.FireStation;
import org.safetynet.domain.Person;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.service.FireStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FireStationController {

    private final FireStationService fireStationService;

    @GetMapping("/firestation")
    private ResponseEntity<List<FireStationEntity>> getFireStations() throws IOException {
        return new ResponseEntity<>(fireStationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/firestation")
    private ResponseEntity<FireStationEntity> createFireStation(@Valid @RequestBody FireStationEntity fireStation) throws IOException{
        return new ResponseEntity<>(fireStationService.save(fireStation),HttpStatus.CREATED);
    }

    @PutMapping("/firestation")
    private  ResponseEntity<FireStation> updateFireStation(@RequestBody FireStation fireStation) throws IOException{
        return new ResponseEntity<>(fireStationService.update(fireStation),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/firestation")
    private ResponseEntity<GenericResponseModel> deleteFireStation(@RequestBody FireStationEntity fireStation) throws IOException{
        return new ResponseEntity<>(fireStationService.delete(fireStation), HttpStatus.OK);
    }
}
