package org.safetynet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.impl.MedicalRecordServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MedicalRecordServiceTest {

    private static MedicalRecordService medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    public void setupPerTest() {
        MockitoAnnotations.openMocks(this);
        medicalRecordService = new MedicalRecordServiceImpl(medicalRecordRepository);
    }

    @Test
    public void testFindAll() throws IOException {
        List<MedicalRecordEntity> expectedMedicalRecordEntities = Arrays.asList(MedicalRecordEntity.builder().firstName("Test").lastName("Test").birthdate("01/01/1970").medications(null).allergies(null).build(), MedicalRecordEntity.builder().firstName("Test2").lastName("Test2").birthdate("02/01/1970").medications(null).allergies(null).build());

        when(medicalRecordRepository.findAll()).thenReturn(expectedMedicalRecordEntities);

        List<MedicalRecordEntity> medicalRecordEntities = medicalRecordService.findAll();

        assertThat(medicalRecordEntities).isEqualTo(expectedMedicalRecordEntities);
    }

    @Test
    public void testSave() throws IOException {
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder().firstName("Test").lastName("Test").birthdate("01/01/1970").medications(null).allergies(null).build();

        when(medicalRecordRepository.save(medicalRecordEntity)).thenReturn(medicalRecordEntity);

        assertThat(medicalRecordService.save(medicalRecordEntity)).isEqualTo(medicalRecordEntity);
    }

    @Test
    public void testUpdate() throws IOException {
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder().firstName("Test").lastName("Test").birthdate("01/01/1970").medications(null).allergies(null).build();

        when(medicalRecordRepository.save(medicalRecordEntity)).thenReturn(medicalRecordEntity);

        medicalRecordService.update(medicalRecordEntity);

        assertThat(medicalRecordService.save(medicalRecordEntity)).isEqualTo(medicalRecordEntity);
    }

    @Test
    public void testDelete_return_success_when_medicalRecord_is_valid() throws IOException {
        String firstName = "Test";
        String lastName = "Test";

        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(true);

        GenericResponseModel response = medicalRecordService.delete(firstName, lastName);

        assertThat(response.success()).isTrue();
        assertThat(response.details()).isEqualTo(String.format("The medical record for %s %s has been successfully deleted !", firstName, lastName));
    }

    @Test
    public void testDelete_return_false_when_medicalRecord_is_not_valid() throws IOException {
        String firstName = "Test";
        String lastName = "Test";

        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(false);

        GenericResponseModel response = medicalRecordService.delete(firstName, lastName);

        assertThat(response.success()).isFalse();
        assertThat(response.details()).isEqualTo(String.format("Medical record for %s %s not found", firstName, lastName));
    }
}
