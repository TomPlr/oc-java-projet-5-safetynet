package org.safetynet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.dto.*;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.impl.PersonServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Spy
    private PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Spy
    private MedicalRecordMapper medicalRecordMapper = Mappers.getMapper(MedicalRecordMapper.class);

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    public void testFindAll() {
        List<PersonDto> expectedPersonDtos = List.of(new PersonDto("Test1", "Test1", "123 Test Rd", "Test City", "42", "123456789", "email@email.com"));

        when(personRepository.findAll()).thenReturn(expectedPersonDtos);

        List<PersonDto> result = personService.findAll();

        assertThat(result).isEqualTo(expectedPersonDtos);
    }

    @Test
    public void testSave() {
        PersonDto expectedPersonDto = mock(PersonDto.class);

        when(personRepository.save(expectedPersonDto)).thenReturn(expectedPersonDto);

        PersonDto result = personService.save(expectedPersonDto);

        assertThat(result).isEqualTo(expectedPersonDto);
    }

    @Test
    public void testUpdate() {
        PersonDto expectedPersonDto = mock(PersonDto.class);
        PersonLiteDto expectedPersonLiteDto = mock(PersonLiteDto.class);

        when(personRepository.update(expectedPersonDto)).thenReturn(expectedPersonLiteDto);

        PersonLiteDto result = personService.update(expectedPersonDto);

        assertThat(result).isEqualTo(expectedPersonLiteDto);
    }

    @Test
    public void testDelete_when_personEntity_is_valid() {
        String firstName = "John";
        String lastName = "Doe";

        when(personRepository.delete(firstName, lastName)).thenReturn(true);

        GenericResponseDto response = personService.delete(firstName, lastName);

        assertThat(response.success()).isTrue();
        assertThat(response.details()).isEqualTo(String.format("%s %s has been successfully deleted !", firstName, lastName));
    }

    @Test
    public void testDelete_when_personEntity_does_not_exist() {
        String firstName = "John";
        String lastName = "Doe";

        when(personRepository.delete(firstName, lastName)).thenReturn(false);

        GenericResponseDto response = personService.delete(firstName, lastName);

        assertThat(response.success()).isFalse();
        assertThat(response.details()).isEqualTo(String.format("Error: %s %s not found!", firstName, lastName));
    }

    @Test
    public void testFindPersonsCoveredByFireStation_when_firestation_exists() {
        int fireStationNumber = 1;
        List<PersonLiteDto> personDtoList = List.of(mock(PersonLiteDto.class), mock(PersonLiteDto.class));
        PersonsWithAgeRepartitionDto expectedResult = new PersonsWithAgeRepartitionDto(personDtoList, 2, 0);

        when(personRepository.findPersonsByStationNumber(1)).thenReturn(expectedResult);

        PersonsWithAgeRepartitionDto result = personService.findPersonsCoveredByFireStation(fireStationNumber);

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    public void testFindChildrenByAddress() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<PersonDto> personDtos = List.of(
                new PersonDto("John", "Doe", "123 Test Rd", "Test City", "42", "123456789", "email@email.com"),
                new PersonDto("Jane", "Doe", "123 Test Rd", "Test City", "42", "123456789", "email@email.com")
        );
        List<MedicalRecordDto> medicalRecordDtos = List.of(
                new MedicalRecordDto("John", "Doe", currentDate.minusYears(5).format(formatter), null, null),
                new MedicalRecordDto("Jane", "Doe", currentDate.minusYears(40).format(formatter), null, null)
        );

        when(personRepository.findPersonsByAddress("123 Test St")).thenReturn(personDtos);
        when(medicalRecordRepository.findMedicalRecordsByPersons(personDtos)).thenReturn(medicalRecordDtos);

        List<ChildDto> result = personService.findChildrenByAddress("123 Test St");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).familyMembers()).hasSize(1);
    }

    @Test
    public void testFindPerson() {
        List<String> addresses = List.of("123 Test Rd");
        TreeSet<String> personsPhoneNumbers = new TreeSet<>();
        personsPhoneNumbers.add("123456789");

        when(personRepository.findPersonsPhoneNumbersByAddresses(addresses)).thenReturn(personsPhoneNumbers);

        TreeSet<String> result = personRepository.findPersonsPhoneNumbersByAddresses(addresses);

        assertThat(result).isEqualTo(personsPhoneNumbers);
    }

    @Test
    public void testFindPersons() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto(null, null);
        List<PersonDto> personDtos = List.of(
                new PersonDto("John", "Doe", "123 Test Rd", "Test City", "42", "123456789", "email@email.com")

        );
        List<MedicalRecordDto> medicalRecordDtos = List.of(
                new MedicalRecordDto("John", "Doe", currentDate.minusYears(42).format(formatter), null, null)
        );
        List<PersonExtendedDto> expectedPersons = List.of(new PersonExtendedDto("John", "Doe", "123 Test Rd", "email@email.com", 42, "123456789", medicalHistoryDto, new ArrayList<>()));

        when(personRepository.findPersonsByAddress("123 Test Rd")).thenReturn(personDtos);
        when(medicalRecordRepository.findMedicalRecordsByPersons(personDtos)).thenReturn(medicalRecordDtos);

        List<PersonExtendedDto> result = personService.findPersons("123 Test Rd");

        assertThat(result).hasSize(1);
        assertThat(result).isEqualTo(expectedPersons);
    }


    @Test
    public void testFindPersonEmail() {
        String city = "Test City";
        TreeSet<String> personsEmail = new TreeSet<>();
        personsEmail.add("email@email.com");

        when(personRepository.findEmailsByCity(city)).thenReturn(personsEmail);

        TreeSet<String> result = personService.findPersonsEmail(city);

        assertThat(result).isEqualTo(personsEmail);
    }

    @Test
    public void testGetMedicalRecordsByPerson() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<MedicalRecordDto> medicalRecordDtos = List.of(
                new MedicalRecordDto("John", "Doe", currentDate.minusYears(42).format(formatter), null, null),
                new MedicalRecordDto("John", "Doe", currentDate.minusYears(24).format(formatter), null, null)
        );
        final String firstName = "John";
        final String lastName = "Doe";

        final MedicalRecordDto expectedMedicalRecordDto = new MedicalRecordDto("John", "Doe", currentDate.minusYears(42).format(formatter), null, null);

        MedicalRecordDto result = personService.getMedicalRecordByPerson(firstName, lastName, medicalRecordDtos);

        assertThat(result.firstName()).isEqualTo(expectedMedicalRecordDto.firstName());
        assertThat(result.lastName()).isEqualTo(expectedMedicalRecordDto.lastName());
        assertThat(result.birthdate()).isEqualTo(expectedMedicalRecordDto.birthdate());
    }

    @Test
    public void testCalculateAge_when_medicalRecord_exists() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final MedicalRecordDto medicalRecordDto = new MedicalRecordDto("John", "Doe", currentDate.minusYears(42).format(formatter), null, null);

        long result = personService.calculateAge(medicalRecordDto);

        assertThat(result).isEqualTo(42L);
    }

    @Test
    public void testCalculateAge_when_medicalRecord_doesNotExist() {
        long result = personService.calculateAge(null);

        assertThat(result).isEqualTo(0);
    }
}


