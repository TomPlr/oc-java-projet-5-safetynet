package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.model.ChildModel;
import org.safetynet.model.PersonsByStationModel;
import org.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.safetynet.repository.impl.DataLoadJson.*;


@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonMapper mapper;

    @Override
    public List<PersonEntity> findAll() {
        return PERSON_ENTITIES;
    }

    @Override
    public PersonEntity save(PersonEntity personEntity) {
        PERSON_ENTITIES.add(personEntity);

        Optional<PersonEntity> optionalPersonEntity = PERSON_ENTITIES
                .stream()
                .filter(person -> person.equals(personEntity))
                .findFirst();

        return optionalPersonEntity.orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    @Override
    public boolean delete(String firstName, String lastName) {
        return PERSON_ENTITIES
                .removeIf(personEntity ->
                        personEntity.getFirstName().equalsIgnoreCase(firstName) && personEntity.getLastName().equalsIgnoreCase(lastName));
    }


    @Override
    public PersonDto update(PersonEntity personEntity) {
        Optional<PersonEntity> optionalPersonEntity = PERSON_ENTITIES
                .stream()
                .filter(person ->
                        personEntity.getFirstName().equalsIgnoreCase(person.getFirstName()) && personEntity.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst();


        return optionalPersonEntity
                .map(person -> {
                    person.setAddress(personEntity.getAddress());
                    person.setCity(personEntity.getCity());
                    person.setEmail(personEntity.getEmail());
                    person.setZip(personEntity.getZip());
                    person.setPhone(personEntity.getPhone());

                    return mapper.personEntityToDto(person);
                })
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    @Override
    public PersonsByStationModel findPersonsByStationNumber(int stationNumber) {
        final int AGE_OF_MAJORITY = 18;

        int totalOver18 = 0;
        int totalUnderOrEqual18 = 0;


        List<String> fireStationsAddresses = FIRE_STATION_ENTITIES.stream()
                .filter(fireStationEntity -> fireStationEntity.getStation() == stationNumber)
                .map(FireStationEntity::getAddress)
                .toList();

        List<PersonDto> persons = PERSON_ENTITIES.stream()
                .filter(personEntity -> fireStationsAddresses.contains(personEntity.getAddress()))
                .map(mapper::personEntityToDto)
                .toList();

        for (PersonDto person : persons) {
            MedicalRecordEntity medicalRecord = MEDICAL_RECORDS_ENTITIES.stream()
                    .filter(record -> record.getFirstName().equals(person.getFirstName())
                            && record.getLastName().equals(person.getLastName()))
                    .findFirst()
                    .orElse(null);

            if (medicalRecord != null) {
                LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                long numberOfYear = birthdate.until(LocalDate.now(), ChronoUnit.YEARS);

                if (numberOfYear > AGE_OF_MAJORITY) {
                    totalOver18++;
                } else {
                    totalUnderOrEqual18++;
                }
            }
        }

        return new PersonsByStationModel(persons, totalOver18, totalUnderOrEqual18);
    }

    @Override
    public List<ChildModel> findPersonsByAddress(String address) {
        List<ChildModel> children = new ArrayList<>();

        List<PersonEntity> persons = PERSON_ENTITIES.stream()
                .filter(personEntity -> address.contains(personEntity.getAddress()))
                .toList();

        for (PersonEntity person : persons) {
            MedicalRecordEntity medicalRecord = MEDICAL_RECORDS_ENTITIES.stream()
                    .filter(record -> record.getFirstName().equals(person.getFirstName())
                            && record.getLastName().equals(person.getLastName()))
                    .findFirst()
                    .orElse(null);

            if (medicalRecord != null) {
                LocalDate today = LocalDate.now();
                LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

                int age = Period.between(birthDate, today).getYears();


                if (age < 18) {
                    children.add(mapper.toPersonWithAgeModel(person, age, new ArrayList<>()));

                }
            }
        }

        for (ChildModel child : children) {
            List<PersonDto> familyMembers = new ArrayList<>();

            persons.stream()
                    .filter(person -> !child.firstName().equalsIgnoreCase(person.getFirstName())
                            || !child.lastName().equalsIgnoreCase(person.getLastName()))
                    .forEach(person -> familyMembers.add(mapper.personEntityToDto(person)));

            familyMembers.forEach(familyMember -> child.familyMembers().add(familyMember));
        }

        return children;
    }

    @Override
    public TreeSet<String> findPersonsPhoneNumbersByAddresses(List<String> addresses) {
        TreeSet<String> phoneNumbers = new TreeSet<>();

        PERSON_ENTITIES.stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .forEach(person -> phoneNumbers.add(person.getPhone()));

        return phoneNumbers;
    }
}
