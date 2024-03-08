package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.domain.Persons;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.entity.MedicalRecordEntity;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

                    return mapper.personEntityToDto(personEntity);
                })
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    @Override
    public Persons findPersonsByStationNumber(int stationNumber) {
        final int AGE_OF_MAJORITY = 18;

        Persons personsByStation = new Persons();
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


        personsByStation.setPersons(persons);
        personsByStation.setTotalOver18(totalOver18);
        personsByStation.setTotalUnderOrEqual18(totalUnderOrEqual18);

        return personsByStation;
    }
}
