package org.safetynet.controller;

import lombok.AllArgsConstructor;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.TreeSet;

@RestController
@RequestMapping("/communityEmail")
@AllArgsConstructor
public class CommunityEmailController {

    PersonService personService;

    @GetMapping
    public ResponseEntity<TreeSet<String>> getEmails(@RequestParam String city) throws IOException {
        return new ResponseEntity<>(personService.getPersonsEmail(city), HttpStatus.OK);
    }

}
