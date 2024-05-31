package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
class FireStationRepositoryTest {

    @Autowired
    private FireStationRepository fireStationRepository;

    @BeforeEach
    public void setUp() {
        DataLoadJson.init();
    }

    @Test
    public void findAllTest() {
        List<FireStationDto> expectedFireStationDto = fireStationRepository.findAll();

        assertThat(expectedFireStationDto).hasSize(13);
    }

    @Test
    public void testSave() {
        FireStationDto expectedFireStation = new FireStationDto("Address1",1);

        FireStationDto result = fireStationRepository.save(expectedFireStation);

        assertThat(result).isEqualTo(expectedFireStation);
    }

    @Test
    public void testUpdate_return_updated_firestation() {
        FireStationDto expectedFireStation = new FireStationDto("1509 Culver St",10);

        FireStationDto expectedResult = fireStationRepository.update(expectedFireStation);

        assertThat(expectedResult.station()).isEqualTo(10);
    }


    @Test
    public void testDelete_return_true() {
        FireStationDto expectedFireStation = new FireStationDto("1509 Culver St",3);

        boolean result = fireStationRepository.delete(expectedFireStation);

        assertThat(result).isTrue();
    }

    @Test
    public void testDelete_return_false() {
        FireStationDto expectedFireStation = new FireStationDto("123 Test Rd",1);

        boolean expectedResult = fireStationRepository.delete(expectedFireStation);

        assertThat(expectedResult).isFalse();
    }

    @Test
    public void findAddressesByStation_shouldReturnAddresses() {
        List<String> addresses = List.of("908 73rd St", "644 Gershwin Cir", "947 E. Rose Dr");

        List<String> expectedAddresses = fireStationRepository.findAddressesByStation(1);

        assertThat(expectedAddresses).containsExactlyInAnyOrderElementsOf(addresses);
    }

    @Test
    public void findFireStationByAddress_shouldReturnFireStation() {
        FireStationEntity expectedFireStationEntity = FireStationEntity.builder().station(1).address("908 73rd St").build();

        FireStationDto result = fireStationRepository.findFireStation("908 73rd St");

        assertThat(result.address()).isEqualTo(expectedFireStationEntity.getAddress());
        assertThat(result.station()).isEqualTo(expectedFireStationEntity.getStation());

    }

}
