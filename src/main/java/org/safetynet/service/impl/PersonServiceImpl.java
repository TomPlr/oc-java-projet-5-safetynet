package org.safetynet.service.impl;


import lombok.AllArgsConstructor;
import org.safetynet.dto.*;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.MedicalRecordMapper;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.dto.GenericResponseDto;
import org.safetynet.dto.PersonExtendedDto;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.repository.MedicalRecordRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
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
    public GenericResponseDto delete(String firstName, String lastName) {
        final boolean isSuccessfullyDeleted = personRepository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return new GenericResponseDto(true, String.format("%s %s has been successfully deleted !", firstName, lastName));
        } else {
            return new GenericResponseDto(false, String.format("Error: %s %s not found!", firstName, lastName));
        }
    }

    @Override
    public PersonDto update(PersonEntity person) {
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
            if (person.age() < 18){

                List<PersonDto> familyMembers = persons.stream()
                        .filter(personModel -> !person.equals(personModel))
                        .map(personModel -> personMapper.toPersonDto(personModel))
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
    public PersonExtendedDto findPerson(String firstName, String lastName){

        PersonEntity personEntity = personRepository.findPersonByName(firstName,lastName);
        MedicalRecordEntity medicalRecord = medicalRecordRepository.findMedicalRecordByName(firstName, lastName);
        long age = calculateAge(medicalRecord);

        return personMapper.toPersonExtendedDto(personEntity, age, medicalRecordMapper.toMedicalHistory(medicalRecord),new ArrayList<>());
    }

    @Override
    public List<PersonExtendedDto> findPersons(String address){
        List<MedicalRecordEntity> medicalRecords = new ArrayList<>();
        List<PersonExtendedDto> persons = new ArrayList<>();

        List<PersonEntity> personEntities = personRepository.findPersonsByAddress(address);

        if (!personEntities.isEmpty()) {
            medicalRecords = medicalRecordRepository.findMedicalRecordsByPersons(personEntities);
        }

        for (PersonEntity personEntity : personEntities) {
            MedicalRecordEntity medicalRecord = getMedicalRecordByPerson(personEntity.getFirstName(), personEntity.getLastName(), medicalRecords);
            long age = calculateAge(medicalRecord);

            persons.add(personMapper.toPersonExtendedDto(personEntity, age, medicalRecordMapper.toMedicalHistory(medicalRecord),new ArrayList<>()));
        }

        return persons;
    }


    @Override
    public TreeSet<String> findPersonsEmail(String city) throws IOException {
        return personRepository.findEmailsByCity(city);
    }

    public MedicalRecordEntity getMedicalRecordByPerson(String firstName, String lastName, List<MedicalRecordEntity> medicalRecords) {
        return medicalRecords.stream()
                .filter(medicalRecordEntity -> medicalRecordEntity.getLastName().equalsIgnoreCase(lastName) && medicalRecordEntity.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }

    public long calculateAge(MedicalRecordEntity medicalRecord) {
        if (medicalRecord == null) {
            return 0;
        }
        LocalDate birthdate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return birthdate.until(LocalDate.now(), ChronoUnit.YEARS);
    }
}
