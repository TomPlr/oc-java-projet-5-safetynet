package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.dto.PersonDto;
import org.safetynet.dto.PersonLiteDto;
import org.safetynet.dto.PersonsWithAgeRepartitionDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest(classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        DataLoadJson.init();
    }

    @Test
    public void testFindAll() {
        final List<PersonDto> personDtos = personRepository.findAll();

        assertThat(personDtos).hasSize(23);
    }

    @Test
    public void testSave() {
        final PersonDto personDto =
                new PersonDto("John", "Doe", "123 Test Rd", "Test City", "42", "123456789", "email@email.com");

        PersonDto result = personRepository.save(personDto);

        assertThat(result).isEqualTo(personDto);
    }

    @Test
    public void testDelete_return_true_if_person_exists() {
        boolean result = personRepository.delete("John", "Boyd");

        assertThat(result).isTrue();

    }

    @Test
    public void testDelete_return_false_if_person_does_not_exist() {
        boolean result = personRepository.delete("John", "Test");

        assertThat(result).isFalse();
    }

    @Test
    public void testUpdate_return_updated_person_if_person_exists() {
        final PersonDto personDto =
                new PersonDto("John", "Boyd", "123 Test Rd", "Test City", "42", "123456789", "email@email.com");

        PersonLiteDto result = personRepository.update(personDto);

        assertThat(result.address()).isEqualTo(personDto.address());
    }

    @Test
    public void testUpdate_throw_exception_if_person_does_not_exist() {
        final PersonDto personDto =
                new PersonDto("John", "Doe", "123 Test Rd", "Test City", "42", "123456789", "email@email.com");

        assertThatThrownBy(() -> personRepository.update(personDto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testFindPersonsByStationNumber() {
        PersonsWithAgeRepartitionDto result = personRepository.findPersonsByStationNumber(1);

        assertThat(result.persons()).hasSize(6);
        assertThat(result.totalUnderOrEqual18()).isEqualTo(1);
        assertThat(result.totalOver18()).isEqualTo(5);
    }

    @Test
    public void testFindPersonsByAddress() {
        List<PersonDto> result = personRepository.findPersonsByAddress("1509 Culver St");

        assertThat(result).hasSize(5);
    }

    @Test
    public void testFindPersonsPhoneNumbersByAddresses() {
        List<String> phoneNumbers = List.of("841-874-6513", "841-874-6512", "841-874-6544");
        List<String> addresses = List.of("1509 Culver St");

        TreeSet<String> result = personRepository.findPersonsPhoneNumbersByAddresses(addresses);

        assertThat(result).containsExactlyInAnyOrderElementsOf(phoneNumbers);
    }

    @Test
    public void testFindPersonByName_return_person_if_person_exists() {
        PersonDto result = personRepository.findPersonByName("John", "Boyd");

        assertThat(result).isNotNull();
    }

    @Test
    public void testFindPersonByName_return_null_if_person_does_not_exist() {
        PersonDto result = personRepository.findPersonByName("John", "Test");

        assertThat(result).isNull();
    }

    @Test
    public void findEmailsByCity_shouldReturnEmails() {
        TreeSet<String> result = personRepository.findEmailsByCity("Culver");

        assertThat(result).hasSize(15);
    }
}
