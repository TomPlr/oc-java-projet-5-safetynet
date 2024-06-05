package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class MedicalRecordRepositoryTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    public void setUp() {
        DataLoadJson.init();
    }

    @Test
    public void testFindAll() {
        List<MedicalRecordDto> medicalRecordDtos = medicalRecordRepository.findAll();

        assertThat(medicalRecordDtos).hasSize(23);
    }

    @Test
    public void testSave() {
        final MedicalRecordDto expectedMedicalRecordDto =  new MedicalRecordDto("John","Doe","01/01/1970" ,null,null);

        MedicalRecordDto result = medicalRecordRepository.save(expectedMedicalRecordDto);

        assertThat(result).isEqualTo(expectedMedicalRecordDto);
    }

    @Test
    public void testUpdate() {
        final MedicalRecordDto expectedMedicalRecordDto =  new MedicalRecordDto("John","Boyd","01/01/1970" ,null,null);

        MedicalRecordDto result = medicalRecordRepository.update(expectedMedicalRecordDto);

        assertThat(result.birthdate()).isEqualTo("01/01/1970");
    }

    @Test
    public void testDelete_return_true_if_medical_record_exists() {
        boolean result = medicalRecordRepository.delete("John", "Boyd");

        assertThat(result).isTrue();
    }

    @Test
    public void testDelete_return_false_if_medical_record_does_not_exist() {
        boolean result = medicalRecordRepository.delete("John", "Doe");

        assertThat(result).isFalse();
    }

    @Test
    public void testFindMedicalRecordByName_return_medical_record_if_person_exists() {
        MedicalRecordDto result = medicalRecordRepository.findMedicalRecordByName("John", "Boyd");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindMedicalRecordByName_return_null_if_person_does_not_exist() {
        MedicalRecordDto result = medicalRecordRepository.findMedicalRecordByName("John", "Test");

        assertThat(result).isNull();
    }

    @Test
    public void testFindMedicalRecordsByPersons_return_medical_records_if_person_exists() {
        List<PersonDto> personDtos = List.of(
                new PersonDto("John","Boyd","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com")

        );

        List<MedicalRecordDto> result = medicalRecordRepository.findMedicalRecordsByPersons(personDtos);

        assertThat(result).hasSize(1);
    }

    @Test
    public void testFindMedicalRecordsByPersons_return_null_if_person_does_not_exist() {
        List<PersonDto> personDtos = List.of(
                new PersonDto("John","Doe","1509 Culver St","Culver","97451","841-874-6512","jaboyd@email.com")

        );

        List<MedicalRecordDto> result = medicalRecordRepository.findMedicalRecordsByPersons(personDtos);

        assertThat(result).hasSize(0);
    }
}
