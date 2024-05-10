package org.safetynet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.dto.*;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.impl.PersonServiceImpl;

import java.io.IOException;
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
        List<PersonEntity> expectedPersonEntities = List.of(PersonEntity.builder().firstName("Test1").lastName("Test1").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build());

        when(personRepository.findAll()).thenReturn(expectedPersonEntities);

        List<PersonEntity> actualPersonEntities = personService.findAll();

        assertThat(actualPersonEntities).isEqualTo(expectedPersonEntities);
    }

    @Test
    public void testSave() {
        PersonEntity personEntity = mock(PersonEntity.class);

        when(personRepository.save(personEntity)).thenReturn(personEntity);

        PersonEntity actualPersonEntity = personService.save(personEntity);

        assertThat(actualPersonEntity).isEqualTo(personEntity);
    }

    @Test
    public void testUpdate() {
        PersonEntity personEntity = mock(PersonEntity.class);
        PersonDto expectedPersonDto = mock(PersonDto.class);

        when(personRepository.update(personEntity)).thenReturn(expectedPersonDto);

        PersonDto result = personService.update(personEntity);

        assertThat(result).isEqualTo(expectedPersonDto);
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
        List<PersonDto> personDtoList = List.of(mock(PersonDto.class), mock(PersonDto.class));
        PersonsWithAgeRepartitionDto expectedResult = new PersonsWithAgeRepartitionDto(personDtoList, 2, 0);

        when(personRepository.findPersonsByStationNumber(1)).thenReturn(expectedResult);

        PersonsWithAgeRepartitionDto result = personService.findPersonsCoveredByFireStation(fireStationNumber);

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    public void testFindChildrenByAddress() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        List<PersonEntity> personEntities = List.of(
                PersonEntity.builder().firstName("John").lastName("Doe").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build(),
                PersonEntity.builder().firstName("Jane").lastName("Doe").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build()
        );
        List<MedicalRecordEntity> medicalRecordEntities = List.of(
                MedicalRecordEntity.builder().firstName("John").lastName("Doe").birthdate(currentDate.minusYears(5).format(formatter)).medications(null).allergies(null).build(),
                MedicalRecordEntity.builder().firstName("Jane").lastName("Doe").birthdate(currentDate.minusYears(40).format(formatter)).medications(null).allergies(null).build()
        );

        when(personRepository.findPersonsByAddress("123 Test St")).thenReturn(personEntities);
        when(medicalRecordRepository.findMedicalRecordsByPersons(personEntities)).thenReturn(medicalRecordEntities);

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
        List<PersonEntity> personEntities = List.of(
                PersonEntity.builder().firstName("John").lastName("Doe").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build()
        );
        List<MedicalRecordEntity> medicalRecordEntities = List.of(
                MedicalRecordEntity.builder().firstName("John").lastName("Doe").birthdate(currentDate.minusYears(42).format(formatter)).medications(null).allergies(null).build()
        );
        List<PersonExtendedDto> expectedPersons = List.of(new PersonExtendedDto("John", "Doe", "123 Test Rd", "email@email.com", 42, "123456789", medicalHistoryDto, new ArrayList<>()));

        when(personRepository.findPersonsByAddress("123 Test Rd")).thenReturn(personEntities);
        when(medicalRecordRepository.findMedicalRecordsByPersons(personEntities)).thenReturn(medicalRecordEntities);

        List<PersonExtendedDto> result = personService.findPersons("123 Test Rd");

        assertThat(result).hasSize(1);
        assertThat(result).isEqualTo(expectedPersons);
    }


    @Test
    public void testFindPersonEmail() throws IOException {
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
        final List<MedicalRecordEntity> medicalRecordEntities = List.of(
                MedicalRecordEntity.builder().firstName("John").lastName("Doe").birthdate(currentDate.minusYears(42).format(formatter)).medications(null).allergies(null).build(),
                MedicalRecordEntity.builder().firstName("Jane").lastName("Doe").birthdate(currentDate.minusYears(24).format(formatter)).medications(null).allergies(null).build()
        );
        final String firstName = "John";
        final String lastName = "Doe";

        final MedicalRecordEntity expectedMedicalRecordEntity = MedicalRecordEntity.builder().firstName("John").lastName("Doe").birthdate(currentDate.minusYears(42).format(formatter)).medications(null).allergies(null).build();

        MedicalRecordEntity result = personService.getMedicalRecordByPerson(firstName, lastName, medicalRecordEntities);

        assertThat(result.getFirstName()).isEqualTo(expectedMedicalRecordEntity.getFirstName());
        assertThat(result.getLastName()).isEqualTo(expectedMedicalRecordEntity.getLastName());
        assertThat(result.getBirthdate()).isEqualTo(expectedMedicalRecordEntity.getBirthdate());
    }

    @Test
    public void testCalculateAge_when_medicalRecord_exists() {
        final LocalDate currentDate = LocalDate.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder().firstName("John").lastName("Doe").birthdate(currentDate.minusYears(42).format(formatter)).medications(null).allergies(null).build();

        long result = personService.calculateAge(medicalRecordEntity);

        assertThat(result).isEqualTo(42L);
    }

    @Test
    public void testCalculateAge_when_medicalRecord_doesNotExist() {
        final MedicalRecordEntity medicalRecordEntity = null;

        long result = personService.calculateAge(null);

        assertThat(result).isEqualTo(0);
    }
}


