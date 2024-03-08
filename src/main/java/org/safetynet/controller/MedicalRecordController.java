package org.safetynet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.GenericResponseModel;
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
    private ResponseEntity<List<MedicalRecordEntity>> getMedicalRecords() throws IOException {
        return new ResponseEntity<>(medicalRecordService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<MedicalRecordDto> createMedicalRecord(@Valid @RequestBody MedicalRecordDto medicalRecord) throws IOException {
        return new ResponseEntity<>(medicalRecordService.save(medicalRecord), HttpStatus.CREATED);
    }

    @PutMapping
    private ResponseEntity<MedicalRecordDto> updateMedicalRecord(@Valid @RequestBody MedicalRecordDto medicalRecord) throws IOException {
        return new ResponseEntity<>(medicalRecordService.save(medicalRecord), HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity<GenericResponseModel> deleteMedicalRecord(@RequestParam String firstName,@RequestParam String lastName) throws IOException{
        return new ResponseEntity<>(medicalRecordService.delete(firstName,lastName), HttpStatus.OK);
    }

}
