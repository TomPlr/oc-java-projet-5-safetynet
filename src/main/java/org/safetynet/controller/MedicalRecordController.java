package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
@AllArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public ResponseEntity<List<MedicalRecordEntity>> findMedicalRecords()  {
        return new ResponseEntity<>(medicalRecordService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordEntity> createMedicalRecord(@Valid @RequestBody MedicalRecordEntity medicalRecord)  {
        return new ResponseEntity<>(medicalRecordService.save(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MedicalRecordEntity> updateMedicalRecord(@Valid @RequestBody MedicalRecordEntity medicalRecord) {
        return new ResponseEntity<>(medicalRecordService.update(medicalRecord), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<GenericResponseDto> deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
        return new ResponseEntity<>(medicalRecordService.delete(firstName,lastName), HttpStatus.OK);
    }

}
