package org.safetynet.repository.impl;

import lombok.AllArgsConstructor;
import org.safetynet.domain.Person;
import org.safetynet.entity.PersonEntity;
import org.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import static org.safetynet.repository.impl.DataLoadJson.PERSON_ENTITIES;

import java.util.List;


@Repository
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public List<PersonEntity> findAll() {
        return PERSON_ENTITIES;
    }

    @Override
    public void save(PersonEntity person) {
        PERSON_ENTITIES.add(person);
    }

    @Override
    public void delete(String firstName, String lastName) {
        PERSON_ENTITIES.removeIf(personEntity -> personEntity.getFirstName().equalsIgnoreCase(firstName) && personEntity.getLastName().equalsIgnoreCase(lastName));
    }

    @Override
    public void update(Person person) {
        PERSON_ENTITIES
                .stream()
                .filter(personEntity -> personEntity.getFirstName().equalsIgnoreCase(person.getFirstName()) && personEntity.getLastName().equalsIgnoreCase(person.getLastName()))
                .findFirst()
                .ifPresentOrElse(
                        personEntity -> {
                            personEntity.setAddress(person.getAddress());
                            personEntity.setCity(person.getCity());
                            personEntity.setEmail(person.getEmail());
                            personEntity.setZip(person.getZip());
                            personEntity.setPhone(person.getPhone());
                        },
                        () -> {
                            throw new RuntimeException("PersonEntity not found");
                        }
                );
    }
}
