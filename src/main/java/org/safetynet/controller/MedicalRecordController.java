package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/medicalRecord")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<MedicalRecordDto>> findMedicalRecords()  {
        log.info("Getting medical records...");
        List<MedicalRecordDto> medicalRecords = medicalRecordService.findAll();
        log.info("Found {} medical records", medicalRecords.size());
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(@Valid @RequestBody MedicalRecordDto medicalRecord)  {
        log.info("Creating medical record...");
        MedicalRecordDto medicalRecordDto = medicalRecordService.save(medicalRecord);
        log.info("Created medical record: {}", medicalRecordDto);
        return new ResponseEntity<>(medicalRecordDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MedicalRecordDto> updateMedicalRecord(@Valid @RequestBody MedicalRecordDto medicalRecord) {
        log.info("Updating medical record...");
        MedicalRecordDto medicalRecordDto = medicalRecordService.update(medicalRecord);
        log.info("Updated medical record: {}", medicalRecordDto);
        return new ResponseEntity<>(medicalRecordDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Deleting medical record...");
        GenericResponseDto genericResponseDto = medicalRecordService.delete(firstName, lastName);
        log.info("{}", (genericResponseDto != null) ? genericResponseDto.details() : "no details");
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

}
