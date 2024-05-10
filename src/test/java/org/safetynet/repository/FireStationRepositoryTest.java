package org.safetynet.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
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
        List<FireStationEntity> expectedFireStationEntities = fireStationRepository.findAll();

        assertThat(expectedFireStationEntities).hasSize(13);
    }

    @Test
    public void testSave() {
        FireStationEntity fireStationEntity = FireStationEntity.builder().station(1).address("123 Test Rd").build();

        FireStationEntity expectedFireStationEntities = fireStationRepository.save(fireStationEntity);

        assertThat(expectedFireStationEntities).isEqualTo(fireStationEntity);
    }

    @Test
    public void testUpdate_shouldReturnUpdatedEntity() {
        FireStationEntity fireStationEntity = FireStationEntity.builder().station(10).address("1509 Culver St").build();

        FireStationEntity expectedResult = fireStationRepository.update(fireStationEntity);

        assertThat(expectedResult.getStation()).isEqualTo(10);
    }


    @Test
    public void testDelete_shouldReturnTrue() {
        FireStationEntity fireStationEntity = FireStationEntity.builder().station(3).address("1509 Culver St").build();
        boolean expectedResult = fireStationRepository.delete(fireStationEntity);

        assertThat(expectedResult).isTrue();
    }

    @Test
    public void testDelete_shouldReturnFalse() {
        FireStationEntity fireStationEntity = FireStationEntity.builder().station(1).address("123 Test Rd").build();
        boolean expectedResult = fireStationRepository.delete(fireStationEntity);

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

        FireStationEntity result = fireStationRepository.findFireStation("908 73rd St");

        assertThat(result.getAddress()).isEqualTo(expectedFireStationEntity.getAddress());
        assertThat(result.getStation()).isEqualTo(expectedFireStationEntity.getStation());

    }

}
