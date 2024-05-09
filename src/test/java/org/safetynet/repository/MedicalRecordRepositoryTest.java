package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
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
    public void findAll_shouldReturnAllMedicalRecords() {
        List<MedicalRecordEntity> medicalRecordRepositories = medicalRecordRepository.findAll();

        assertThat(medicalRecordRepositories).hasSize(23);
    }

    @Test
    public void save_shouldSaveMedicalRecord() {
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder().firstName("Test").lastName("Test").build();

        MedicalRecordEntity result = medicalRecordRepository.save(medicalRecordEntity);

        assertThat(result).isEqualTo(medicalRecordEntity);
    }

    @Test
    public void update_shouldSaveMedicalRecord() {
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder().firstName("John").lastName("Boyd").birthdate("0").build();

        MedicalRecordEntity result = medicalRecordRepository.update(medicalRecordEntity);

        assertThat(result).isNotNull();
    }

    @Test
    public void delete_shouldReturnTrueIfMedicalRecordEntityExists() {
        boolean result = medicalRecordRepository.delete("John", "Boyd");

        assertThat(result).isTrue();
    }

    @Test
    public void delete_shouldReturnFalseIfMedicalRecordEntityDoesNotExist() {
        boolean result = medicalRecordRepository.delete("Test", "Test");

        assertThat(result).isFalse();
    }

    @Test
    public void findMedicalRecordByName_shouldReturnMedicalRecordEntity() {
        MedicalRecordEntity result = medicalRecordRepository.findMedicalRecordByName("John", "Boyd");

        assertThat(result).isNotNull();
    }

    @Test
    public void findMedicalRecordByName_shouldReturnNullIfMedicalRecordEntityDoesNotExist() {
        MedicalRecordEntity result = medicalRecordRepository.findMedicalRecordByName("John", "Test");

        assertThat(result).isNull();
    }

    @Test
    public void findMedicalRecordsByPersons_shouldReturnMedicalRecordEntities() {
        List<PersonEntity> personEntities = List.of(PersonEntity.builder().firstName("John").lastName("Boyd").address("1509 Culver St").city("Culver").zip("97451").phone("841-874-6512").email("jaboyd@email.com").build());

        List<MedicalRecordEntity> result = medicalRecordRepository.findMedicalRecordsByPersons(personEntities);

        assertThat(result).hasSize(1);
    }

    @Test
    public void findMedicalRecordsByPersons_shouldReturnNullIfMedicalRecordEntityDoesNotExist() {
        List<PersonEntity> personEntities = List.of(PersonEntity.builder().firstName("Test").lastName("Test").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build());

        List<MedicalRecordEntity> result = medicalRecordRepository.findMedicalRecordsByPersons(personEntities);

        assertThat(result).hasSize(0);
    }
}
