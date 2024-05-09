package org.safetynet.controller;

import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonWithoutAddressAndEmailDto;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flood")
@AllArgsConstructor
public class FloodController {

    private final PersonService personService;
    private final FireStationService fireStationService;
    private final PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<Map<String, List<PersonWithoutAddressAndEmailDto>>> getPersonsWithMedicalHistory(@RequestParam int[] stations) throws IOException {
        Map<String, List<PersonWithoutAddressAndEmailDto>> personsByAddress = new HashMap<>();
        List<String> addresses = new ArrayList<>();

        for (int station : stations) {
            addresses.addAll(fireStationService.findAddressesByStation(station));
        }

        for (String address : addresses) {
            List<PersonWithoutAddressAndEmailDto> persons = personService.getPersons(address).stream().map(personMapper::toPersonWithoutAddressAndEmailDto).toList();
            personsByAddress.put(address, persons);
        }

        return new ResponseEntity<>(personsByAddress, HttpStatus.OK);
    }
}
