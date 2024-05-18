package org.safetynet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.PersonExtendedDto;
import org.safetynet.dto.PersonWithoutPhoneDto;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("personInfo")
@AllArgsConstructor
public class PersonInfoController {

    PersonService personService;
    PersonMapper personMapper;

    @GetMapping
    public ResponseEntity<PersonWithoutPhoneDto> findPersonInfos(@RequestParam String firstName, @RequestParam String lastName ) {
        log.info("Looking for {} {}...", firstName, lastName);
        PersonExtendedDto personExtendedDto = personService.findPerson(firstName,lastName);
        log.info("Getting needed infos for {} {}...", firstName, lastName);
        PersonWithoutPhoneDto personWithoutPhoneDto = personMapper.toPersonWithoutPhoneDto(personExtendedDto);
        log.info("Found infos for {} {} !", firstName, lastName);
        return new ResponseEntity<>(personWithoutPhoneDto, HttpStatus.OK);
    }
}
