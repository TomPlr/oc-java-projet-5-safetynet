package org.safetynet.service;

import org.safetynet.dto.PersonDto;
import org.safetynet.entity.PersonEntity;
import org.safetynet.model.ChildModel;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.model.PersonWithMedicalHistoryModel;
import org.safetynet.model.PersonsWithAgeRepartitionModel;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public interface PersonService {
    List<PersonEntity> findAll() throws IOException;

    PersonEntity save(PersonEntity person) throws IOException;

    GenericResponseModel delete(String firstName, String lastName) throws IOException;

    PersonDto update(PersonEntity person) throws IOException;

    PersonsWithAgeRepartitionModel getPersonsCoveredByFireStation(int fireStationNumber) throws IOException;

    List<ChildModel> getChildrenByAddress(String address) throws IOException;

    TreeSet<String> getPersonsPhoneNumberByStation(int station) throws IOException;

    List<PersonWithMedicalHistoryModel> getPersonsWithMedicalHistoryByAddress(String address) throws IOException;
}
