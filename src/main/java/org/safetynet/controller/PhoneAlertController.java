package org.safetynet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

@Slf4j
@RestController
@RequestMapping("/phoneAlert")
@AllArgsConstructor
public class PhoneAlertController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<TreeSet<String>> findPersonsPhoneNumbers(@RequestParam int firestation) {
        log.debug("Getting persons for station n°{}", firestation);
        TreeSet<String> persons = personService.findPersonsPhoneNumberByStation(firestation);
        log.info("Found {} phone number(s)", persons.size());
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
}
