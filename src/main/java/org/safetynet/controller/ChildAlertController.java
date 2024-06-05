package org.safetynet.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.ChildDto;
import org.safetynet.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/childAlert")
@AllArgsConstructor
public class ChildAlertController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<ChildDto>> findChildrenByAddress(@RequestParam final String address) {
        log.info("Find children by address : {}", address);
        List<ChildDto> children = personService.findChildrenByAddress(address);
        log.info("Found {}", (children != null) ? children.size() : "0");

        return new ResponseEntity<>(children, HttpStatus.OK);
    }
}
