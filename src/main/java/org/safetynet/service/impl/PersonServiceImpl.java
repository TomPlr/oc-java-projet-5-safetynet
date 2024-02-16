package org.safetynet.service.impl;


import lombok.AllArgsConstructor;

import org.safetynet.domain.Person;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    public List<PersonEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonEntity save(PersonEntity person) {
        repository.save(person);
        return person;
    }

    @Override
    public GenericResponseModel delete(String firstName, String lastName) {
        repository.delete(firstName,lastName);
        return GenericResponseModel.builder().success(true).details(String.format("%s %s has been successfully deleted !", firstName, lastName)).build();
    }

    @Override
    public Person update(Person person) {
        repository.update(person);
        return person;
    }
}
