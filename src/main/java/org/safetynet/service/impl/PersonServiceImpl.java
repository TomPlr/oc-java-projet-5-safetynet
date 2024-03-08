package org.safetynet.service.impl;


import lombok.AllArgsConstructor;
import org.safetynet.domain.Persons;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public PersonEntity save(PersonEntity person) {
        return repository.save(person);
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

    @Override
    public Persons getPersonsCoveredByFireStation(int fireStationNumber) throws IOException {
        return repository.findPersonsByStationNumber(fireStationNumber);
    }

}
