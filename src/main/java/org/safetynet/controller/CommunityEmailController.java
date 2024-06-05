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

import java.io.IOException;
import java.util.TreeSet;

@Slf4j
@RestController
@RequestMapping("/communityEmail")
@AllArgsConstructor
public class CommunityEmailController {

    PersonService personService;

    @GetMapping
    public ResponseEntity<TreeSet<String>> findEmails(@RequestParam String city) {
        log.info("Gathering persons' emails living in {}...", city);
        TreeSet<String> emails = personService.findPersonsEmail(city);
        log.info("Found {} emails", (emails != null)? emails.size() : "0");
        return new ResponseEntity<>(emails, HttpStatus.OK);
    }

}
