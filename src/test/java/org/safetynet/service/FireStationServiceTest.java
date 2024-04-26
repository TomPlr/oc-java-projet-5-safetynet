package org.safetynet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.model.GenericResponseModel;
import org.safetynet.repository.FireStationRepository;
import org.safetynet.service.impl.FireStationServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {

    @InjectMocks
    private FireStationServiceImpl fireStationService;
    @Mock
    private FireStationRepository fireStationRepository;


    @Test
    public void testFindAll() throws IOException {
        List<FireStationEntity> expectedFireStations = Arrays.asList(
                FireStationEntity.builder().address("Address1").station(1).build(),
                FireStationEntity.builder().address("Address2").station(2).build()
        );

        when(fireStationRepository.findAll()).thenReturn(expectedFireStations);

        List<FireStationEntity> actualFireStations = fireStationService.findAll();

        assertThat(actualFireStations).isEqualTo(expectedFireStations);
    }

    @Test
    public void testSave_return_true_when_fireStationEntity_Is_Valid() throws IOException {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();

        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        FireStationEntity actualFireStation = fireStationService.save(fireStation);

        assertThat(actualFireStation).isEqualTo(fireStation);
    }

    @Test
    public void testSave_return_false_when_fireStationEntity_Is_Invalid() {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();
        when(fireStationRepository.save(fireStation)).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> fireStationService.save(fireStation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testDelete_return_true() throws IOException {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();

        when(fireStationRepository.delete(fireStation)).thenReturn(true);

        fireStationService.delete(fireStation);

        assertThat(fireStationRepository.delete(fireStation)).isTrue();
    }

    @Test
    public void testUpdate() throws IOException {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();
        when(fireStationRepository.update(fireStation)).thenReturn(fireStation);

        assertThat(fireStationService.update(fireStation)).isEqualTo(fireStation);
    }

    @Test
    public void testDelete_return_success_when_fireStationEntity_is_valid() throws IOException {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();

        when(fireStationRepository.delete(fireStation)).thenReturn(true);

        GenericResponseModel response = fireStationService.delete(fireStation);

        assertThat(response.success()).isTrue();
        assertThat(response.details())
                .isEqualTo(String.format("Fire station n°%s will no longer operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()));
    }

    @Test
    public void testDelete_return_error_when_fireStationEntity_is_invalid() throws IOException {
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();
        when(fireStationRepository.delete(fireStation)).thenReturn(false);

        GenericResponseModel response = fireStationService.delete(fireStation);

        assertThat(response.success()).isFalse();
        assertThat(response.details())
                .isEqualTo(String.format("Error: Fire station n°%s does not operate from the following address: %s", fireStation.getStation(), fireStation.getAddress()));
    }

    @Test
    public void testGetStation_With_Address() throws IOException {
        String address = "Address1";
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();

        when(fireStationRepository.getFireStation(address)
        ).thenReturn(fireStation);

        assertThat(fireStationService.getStation(address)).isEqualTo(fireStation.getStation());
    }

    @Test
    public void testGetStation_With_Station() throws IOException {
        int station = 1;
        FireStationEntity fireStation = FireStationEntity.builder().address("Address1").station(1).build();

        when(fireStationRepository.getFireStation(station)).thenReturn(fireStation);

        assertThat(fireStationService.getStation(station)).isEqualTo(fireStation.getAddress());
    }
}
