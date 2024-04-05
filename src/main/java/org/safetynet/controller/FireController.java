package org.safetynet.controller;


import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonsWithMedicalHistoryByStationDto;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/fire")
@AllArgsConstructor
public class FireController {

    private final PersonService personService;
    private final FireStationService fireStationService;

    @GetMapping
    private ResponseEntity<PersonsWithMedicalHistoryByStationDto> getPersonsWithMedicalHistory(@RequestParam String address) throws IOException {
        return new ResponseEntity<>(new PersonsWithMedicalHistoryByStationDto(personService.getPersonsWithMedicalHistoryByAddress(address), fireStationService.getStation(address)), HttpStatus.OK);
    }
}
