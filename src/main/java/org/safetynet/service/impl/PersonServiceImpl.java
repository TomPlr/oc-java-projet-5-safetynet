package org.safetynet.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.safetynet.dto.*;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private FireStationRepository fireStationRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private PersonMapper personMapper;
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonDto save(PersonDto person) {
        return personRepository.save(person);
    }

    @Override
    public GenericResponseDto delete(String firstName, String lastName) {
        final boolean isSuccessfullyDeleted = personRepository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseDto(true, String.format("%s %s has been successfully deleted !", firstName, lastName));
        } else {
            return new GenericResponseDto(false, String.format("Error: %s %s not found!", firstName, lastName));
        }
    }

    @Override
    public PersonLiteDto update(PersonDto person) {
        return personRepository.update(person);
    }

    @Override
    public PersonsWithAgeRepartitionDto findPersonsCoveredByFireStation(int fireStationNumber) {
        return personRepository.findPersonsByStationNumber(fireStationNumber);
    }

    @Override
    public List<ChildDto> findChildrenByAddress(String address) {
        List<ChildDto> children = new ArrayList<>();

        List<PersonExtendedDto> persons = findPersons(address);

        for (PersonExtendedDto person : persons) {
            if (person.age() < 18) {

                List<PersonLiteDto> familyMembers = persons.stream()
                        .filter(personLiteDto -> !person.equals(personLiteDto))
                        .map(personLiteDto -> personMapper.toPersonLiteDto(personLiteDto))
                        .toList();

                person.familyMembers().addAll(familyMembers);
                children.add(personMapper.toChildDto(person));
            }
        }

        return children;
    }


    @Override
    public TreeSet<String> findPersonsPhoneNumberByStation(int station) {
        List<String> addresses = fireStationRepository.findAddressesByStation(station);

        return personRepository.findPersonsPhoneNumbersByAddresses(addresses);
    }


    @Override
    public PersonExtendedDto findPerson(String firstName, String lastName) {

        PersonDto personDto = personRepository.findPersonByName(firstName, lastName);
        MedicalRecordDto medicalRecord = medicalRecordRepository.findMedicalRecordByName(firstName, lastName);
        long age = calculateAge(medicalRecord);

        return personMapper.toPersonExtendedDto(personDto, age, medicalRecordMapper.toMedicalHistory(medicalRecord), new ArrayList<>());
    }

    @Override
    public List<PersonExtendedDto> findPersons(String address) {
        List<MedicalRecordDto> medicalRecords = new ArrayList<>();
        List<PersonExtendedDto> persons = new ArrayList<>();

        List<PersonDto> personDtos = personRepository.findPersonsByAddress(address);

        if (!personDtos.isEmpty()) {
            medicalRecords = medicalRecordRepository.findMedicalRecordsByPersons(personDtos);
        }

        for (PersonDto personDto : personDtos) {
            MedicalRecordDto medicalRecord = getMedicalRecordByPerson(personDto.firstName(), personDto.lastName(), medicalRecords);
            long age = calculateAge(medicalRecord);

            persons.add(personMapper.toPersonExtendedDto(personDto, age, medicalRecordMapper.toMedicalHistory(medicalRecord), new ArrayList<>()));
        }

        return persons;
    }


    @Override
    public TreeSet<String> findPersonsEmail(String city) {
        return personRepository.findEmailsByCity(city);
    }

    public MedicalRecordDto getMedicalRecordByPerson(String firstName, String lastName, List<MedicalRecordDto> medicalRecords) {
        return medicalRecords.stream()
                .filter(medicalRecordDto -> medicalRecordDto.lastName().equalsIgnoreCase(lastName) && medicalRecordDto.firstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    public long calculateAge(MedicalRecordDto medicalRecord) {
        if (medicalRecord == null) {
            log.debug("Unable to calculate age because no medical history has been found.");
            return 0;
        }
        log.debug("Age calculation processing...");

        LocalDate birthdate = LocalDate.parse(medicalRecord.birthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate now = LocalDate.now();
        long age = birthdate.until(now, ChronoUnit.YEARS);

        log.debug("If birthdate is {} and current date is {}, calculated age is {}", birthdate, now, age);
        return age;
    }
}
