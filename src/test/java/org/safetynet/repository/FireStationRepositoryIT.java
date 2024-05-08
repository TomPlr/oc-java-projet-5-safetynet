package org.safetynet.repository;

import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.entity.FireStationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
class FireStationRepositoryIT {

    @Autowired
    FireStationRepository fireStationRepository;

    @Test
    public void findAllTest() {
        List<FireStationEntity> expectedFireStationEntities = fireStationRepository.findAll();

        assertThat(expectedFireStationEntities).hasSize(13);
    }

}
