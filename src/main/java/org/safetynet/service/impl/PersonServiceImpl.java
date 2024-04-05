package org.safetynet.service.impl;


import com.sun.source.tree.Tree;
import lombok.AllArgsConstructor;
import org.safetynet.dto.*;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private FireStationRepository fireStationRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private PersonMapper personMapper;
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity save(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public GenericResponseModel delete(String firstName, String lastName) {
        final boolean isSuccessfullyDeleted = personRepository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseModel(true, String.format("%s %s has been successfully deleted !", firstName, lastName));
        } else {
            return new GenericResponseModel(false, String.format("Error: %s %s not found!", firstName, lastName));
        }
    }

    @Override
    public PersonDto update(PersonEntity person) {
        return personRepository.update(person);
    }

    @Override
    public PersonsWithAgeRepartitionDto getPersonsCoveredByFireStation(int fireStationNumber) {
        return personRepository.findPersonsByStationNumber(fireStationNumber);
    }

    @Override
    public List<ChildDto> getChildrenByAddress(String address) {
        List<MedicalRecordEntity> medicalRecords = new ArrayList<>();
        List<ChildDto> children = new ArrayList<>();

        List<PersonEntity> persons = personRepository.findPersonsByAddress(address);

        if (!persons.isEmpty()) {
            medicalRecords = medicalRecordRepository.findMedicalRecordsByPersons(persons);
        }

        for (MedicalRecordEntity medicalRecord : medicalRecords) {
            LocalDate today = LocalDate.now();
            LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));

            int age = Period.between(birthDate, today).getYears();

            PersonEntity personEntity = persons.stream()
                    .filter(person -> person.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName()) && person.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))
                    .findFirst()
                    .orElse(null);


            if (age < 18 && personEntity != null) {
                List<PersonDto> familyMembers = new ArrayList<>();
                persons.stream().filter(person -> !person.equals(personEntity)).forEach(person -> familyMembers.add(personMapper.toPersonDto(person)));
                children.add(personMapper.toPersonWithAgeDto(personEntity, age, familyMembers));
            }
        }

        return children;
    }

    @Override
    public TreeSet<String> getPersonsPhoneNumberByStation(int station) {
        List<String> addresses = fireStationRepository.getAddressesByStation(station);

        return personRepository.findPersonsPhoneNumbersByAddresses(addresses);
    }

    @Override
    public List<PersonWithoutAddressAndEmailDto> getPersonsWithMedicalHistoryByAddress(String address) {
        List<MedicalRecordEntity> medicalRecords = new ArrayList<>();
        List<PersonWithoutAddressAndEmailDto> persons = new ArrayList<>();

        List<PersonEntity> personEntities = personRepository.findPersonsByAddress(address);

        if (!personEntities.isEmpty()) {
            medicalRecords = medicalRecordRepository.findMedicalRecordsByPersons(personEntities);
        }

        for (PersonEntity personEntity : personEntities) {
            long age = 0;
            LocalDate birthdate = null;

            MedicalRecordEntity medicalRecord = medicalRecords.stream()
                    .filter(medicalRecordEntity -> medicalRecordEntity.getLastName().equalsIgnoreCase(personEntity.getLastName()) && medicalRecordEntity.getFirstName().equalsIgnoreCase(personEntity.getFirstName()))
                    .findFirst()
                    .orElse(null);

            if (medicalRecord != null) {
                birthdate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                age = birthdate.until(LocalDate.now(), ChronoUnit.YEARS);
            }

            persons.add(personMapper.toPersonWithoutAddressAndEmailDto(personEntity, age, medicalRecordMapper.toMedicalHistory(medicalRecord)));

        }

        return persons;
    }

    @Override
    public List<PersonWithoutPhoneDto> getPersonInformation(String firstName, String lastName) {
        final List<MedicalRecordEntity> medicalRecords = new ArrayList<>();

        List<PersonEntity> personEntities= personRepository.findPersonsByName(firstName, lastName);
        List<PersonWithoutPhoneDto> persons = new ArrayList<>();

        if (!personEntities.isEmpty()) {
            medicalRecords.addAll(medicalRecordRepository.findMedicalRecordsByPersons(personEntities)) ;
        }

        for (PersonEntity personEntity : personEntities) {
            long age = 0;
            LocalDate birthdate = null;

            MedicalRecordEntity medicalRecord = medicalRecords.stream()
                    .filter(medicalRecordEntity -> medicalRecordEntity.getLastName().equalsIgnoreCase(personEntity.getLastName()) && medicalRecordEntity.getFirstName().equalsIgnoreCase(personEntity.getFirstName()))
                    .findFirst()
                    .orElse(null);

            if (medicalRecord != null) {
                birthdate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                age = birthdate.until(LocalDate.now(), ChronoUnit.YEARS);
            }

            persons.add(personMapper.toPersonWithoutPhoneDto(personEntity, age, medicalRecordMapper.toMedicalHistory(medicalRecord)));
        }

        return persons;
    }

    @Override
    public TreeSet<String> getPersonsEmail(String city) throws IOException {
        return personRepository.findEmailsByCity(city);
    }

}
