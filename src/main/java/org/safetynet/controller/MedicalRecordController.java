package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<MedicalRecordEntity>> findMedicalRecords()  {
        log.info("Getting medical records...");
        List<MedicalRecordEntity> medicalRecords = medicalRecordService.findAll();
        log.info("Found {} medical records", medicalRecords.size());
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordEntity> createMedicalRecord(@Valid @RequestBody MedicalRecordEntity medicalRecord)  {
        log.info("Creating medical record...");
        MedicalRecordEntity medicalRecordEntity = medicalRecordService.save(medicalRecord);
        log.info("Created medical record: {}", medicalRecordEntity);
        return new ResponseEntity<>(medicalRecordEntity, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MedicalRecordEntity> updateMedicalRecord(@Valid @RequestBody MedicalRecordEntity medicalRecord) {
        log.info("Updating medical record...");
        MedicalRecordEntity medicalRecordEntity = medicalRecordService.update(medicalRecord);
        log.info("Updated medical record: {}", medicalRecordEntity);
        return new ResponseEntity<>(medicalRecordEntity, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        log.info("Deleting medical record...");
        GenericResponseDto genericResponseDto = medicalRecordService.delete(firstName, lastName);
        log.info("{}", (genericResponseDto != null) ? genericResponseDto.details() : "no details");
        return new ResponseEntity<>(genericResponseDto, HttpStatus.OK);
    }

}
