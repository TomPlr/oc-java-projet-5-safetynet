package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.dto.PersonDto;
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
    public void findAllTest() {
        List<PersonEntity> personEntities = personRepository.findAll();

        assertThat(personEntities).hasSize(23);
    }

    @Test
    public void save_shouldReturnSavedPerson() {
        PersonEntity personEntity = PersonEntity.builder().firstName("Test").lastName("Test").address("123 Test Rd").city("Test City").zip("42").phone("123456789").email("email@email.com").build();

        PersonEntity result = personRepository.save(personEntity);

        assertThat(result).isEqualTo(personEntity);
    }

    @Test
    public void delete_shouldReturnTrue() {
        boolean result = personRepository.delete("John", "Boyd");

        assertThat(result).isTrue();

    }

    @Test
    public void delete_shouldReturnFalseIfPersonDoesNotExist() {
        boolean result = personRepository.delete("John", "Test");

        assertThat(result).isFalse();
    }

    @Test
    public void update_shouldUpdatedPersonDto() {
        PersonEntity personEntity = PersonEntity.builder().firstName("John").lastName("Boyd").address("123 Test St").build();

        PersonDto result = personRepository.update(personEntity);

        assertThat(result.address()).isEqualTo(personEntity.getAddress());
    }

    @Test
    public void update_shouldThrowExceptionIfPersonDoesNotExist() {
        PersonEntity personEntity = PersonEntity.builder().firstName("John").lastName("Test").address("123 Test St").build();

        assertThatThrownBy(() -> personRepository.update(personEntity))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void findPersonsByStationNumber_shouldReturnPersonsWithAgeRepartitionDto() {
        PersonsWithAgeRepartitionDto result = personRepository.findPersonsByStationNumber(1);

        assertThat(result.persons()).hasSize(6);
        assertThat(result.totalUnderOrEqual18()).isEqualTo(1);
        assertThat(result.totalOver18()).isEqualTo(5);
    }

    @Test
    public void findPersonsByAddress_shouldReturnPersonEntities() {
        List<PersonEntity> result = personRepository.findPersonsByAddress("1509 Culver St");

        assertThat(result).hasSize(5);
    }

    @Test
    public void findPersonsPhoneNumbersByAddresses_shouldReturnPhoneNumbers() {
        List<String> phoneNumbers = List.of("841-874-6513", "841-874-6512", "841-874-6544");
        List<String> addresses = List.of("1509 Culver St");

        TreeSet<String> result = personRepository.findPersonsPhoneNumbersByAddresses(addresses);

        assertThat(result).containsExactlyInAnyOrderElementsOf(phoneNumbers);
    }

    @Test
    public void findPersonByName_shouldReturnPersonEntity() {
        PersonEntity result = personRepository.findPersonByName("John", "Boyd");

        assertThat(result).isNotNull();
    }

    @Test
    public void findPersonByName_shouldReturnNullIfPersonDoesntExist() {
        PersonEntity result = personRepository.findPersonByName("John", "Test");

        assertThat(result).isNull();
    }

    @Test
    public void findEmailsByCity_shouldReturnEmails(){
        TreeSet<String> result= personRepository.findEmailsByCity("Culver");

        assertThat(result).hasSize(15);
    }
}
