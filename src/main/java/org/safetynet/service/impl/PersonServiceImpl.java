package org.safetynet.service.impl;


import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;
    private PersonMapper mapper;

    @Override
    public List<PersonEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public PersonDto save(PersonDto person) {
        return repository.save(mapper.PersonDtoToEntity(person));
    }

    @Override
    public GenericResponseModel delete(String firstName, String lastName) {
        final boolean isSuccessfullyDeleted = repository.delete(firstName, lastName);

        if (isSuccessfullyDeleted) {
            return GenericResponseModel.builder().success(true).details(String.format("%s %s has been successfully deleted !", firstName, lastName)).build();
        } else {
            return GenericResponseModel.builder().success(false).details(String.format("Error: %s %s not found!", firstName, lastName)).build();
        }
    }

    @Override
    public PersonDto update(PersonDto person) {
        return repository.update(mapper.PersonDtoToEntity(person));
    }
}
