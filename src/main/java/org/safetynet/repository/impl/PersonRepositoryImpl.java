package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.safetynet.repository.impl.DataLoadJson.PERSON_ENTITIES;


@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonMapper mapper;

    @Override
    public List<PersonEntity> findAll() {
        return PERSON_ENTITIES;
    }

    @Override
    public PersonDto save(PersonEntity personEntity) {
        PERSON_ENTITIES.add(personEntity);

        Optional<PersonDto> optionalPersonDto = PERSON_ENTITIES
                .stream()
                .filter(person -> person.equals(personEntity))
                .findFirst()
                .map(mapper::personEntityToDto);
        return optionalPersonDto.orElseThrow(() -> new NoSuchElementException("Person not found"));
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
}
