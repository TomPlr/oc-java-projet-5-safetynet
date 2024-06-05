package org.safetynet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.MedicalRecordDto;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.service.impl.MedicalRecordServiceImpl;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    public void testFindAll() throws IOException {
        final List<MedicalRecordDto> expectedMedicalRecordDtos = List.of(
                new MedicalRecordDto("John", "Doe", "01/01/1970", null, null),
                new MedicalRecordDto("John", "Doe", "02/02/1970", null, null)
        );

        when(medicalRecordRepository.findAll()).thenReturn(expectedMedicalRecordDtos);

        List<MedicalRecordDto> result = medicalRecordService.findAll();

        assertThat(result).isEqualTo(expectedMedicalRecordDtos);
    }

    @Test
    public void testSave() {
        final MedicalRecordDto expectedMedicalRecordDto = new MedicalRecordDto("John", "Doe", "01/01/1970", null, null);

        when(medicalRecordRepository.save(expectedMedicalRecordDto)).thenReturn(expectedMedicalRecordDto);

        MedicalRecordDto result = medicalRecordService.save(expectedMedicalRecordDto);

        assertThat(result).isEqualTo(expectedMedicalRecordDto);
    }

    @Test
    public void testUpdate() {
        final MedicalRecordDto expectedMedicalRecordDto = new MedicalRecordDto("John", "Doe", "01/01/1970", null, null);

        when(medicalRecordRepository.update(expectedMedicalRecordDto)).thenReturn(expectedMedicalRecordDto);

        MedicalRecordDto result = medicalRecordService.update(expectedMedicalRecordDto);

        assertThat(result).isEqualTo(expectedMedicalRecordDto);
    }

    @Test
    public void testDelete_return_success_when_medicalRecord_is_valid() {
        String firstName = "Test";
        String lastName = "Test";

        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(true);

        GenericResponseDto response = medicalRecordService.delete(firstName, lastName);

        assertThat(response.success()).isTrue();
        assertThat(response.details()).isEqualTo(String.format("The medical record for %s %s has been successfully deleted !", firstName, lastName));
    }

    @Test
    public void testDelete_return_false_when_medicalRecord_is_not_valid() throws IOException {
        String firstName = "Test";
        String lastName = "Test";

        when(medicalRecordRepository.delete(firstName, lastName)).thenReturn(false);

        GenericResponseDto response = medicalRecordService.delete(firstName, lastName);

        assertThat(response.success()).isFalse();
        assertThat(response.details()).isEqualTo(String.format("Medical record for %s %s not found", firstName, lastName));
    }
}
