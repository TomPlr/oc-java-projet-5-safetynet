package org.safetynet.service.impl;


import lombok.AllArgsConstructor;
import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.model.ChildModel;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.model.PersonsByStationModel;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.repository.PersonRepository;
import org.safetynet.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private FireStationRepository fireStationRepository;
    private PersonMapper mapper;

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
    public PersonsByStationModel getPersonsCoveredByFireStation(int fireStationNumber) throws IOException {
        return personRepository.findPersonsByStationNumber(fireStationNumber);
    }

    @Override
    public List<ChildModel> getChildrenByAddress(String address) throws IOException {
        return personRepository.findPersonsByAddress(address);
    }

    @Override
    public TreeSet<String> getPersonsPhoneNumberByStation(int station) throws IOException {
        List<String> addresses= fireStationRepository.getAddressesByStation(station);
        
        return personRepository.findPersonsPhoneNumbersByAddresses(addresses);
    }
}
