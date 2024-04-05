package org.safetynet.controller;

import lombok.AllArgsConstructor;
import org.safetynet.dto.ChildDto;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/childAlert")
@AllArgsConstructor
public class ChildAlertController {

    private final PersonService personService;

    @GetMapping
    private ResponseEntity<List<ChildDto>> getChildrenByAddress(@RequestParam String address) throws IOException {
        return new ResponseEntity<>(personService.getChildrenByAddress(address), HttpStatus.OK);
    }
}
