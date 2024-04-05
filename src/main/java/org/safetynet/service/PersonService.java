package org.safetynet.service;

import org.safetynet.dto.*;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.*;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public interface PersonService {
    List<PersonEntity> findAll() throws IOException;

    PersonEntity save(PersonEntity person) throws IOException;

    GenericResponseModel delete(String firstName, String lastName) throws IOException;

    PersonDto update(PersonEntity person) throws IOException;

    PersonsWithAgeRepartitionDto getPersonsCoveredByFireStation(int fireStationNumber) throws IOException;

    List<ChildDto> getChildrenByAddress(String address) throws IOException;

    TreeSet<String> getPersonsPhoneNumberByStation(int station) throws IOException;

    List<PersonWithoutAddressAndEmailDto> getPersonsWithMedicalHistoryByAddress(String address) throws IOException;

    List<PersonWithoutPhoneDto> getPersonInformation(String firstName, String lastName) throws IOException;

}
