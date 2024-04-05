package org.safetynet.controller;

import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonWithoutPhoneDto;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("personInfo")
@AllArgsConstructor
public class PersonInfoController {

    PersonService personService;

    @GetMapping
    private ResponseEntity<List<PersonWithoutPhoneDto>> getPersonInfos(@RequestParam String firstName, @RequestParam String lastName ) throws IOException {
        return new ResponseEntity<>(personService.getPersonInformation(firstName,lastName), HttpStatus.OK);
    }
}
