package org.safetynet.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.dto.FireStationDto;
import org.safetynet.entity.FireStationEntity;
import org.safetynet.dto.GenericResponseDto;
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
    public void testFindAll()  {
        List<FireStationDto> expectedFireStations = Arrays.asList(
                new FireStationDto("Address1",1),
                new FireStationDto("Address2",2)
        );

        when(fireStationRepository.findAll()).thenReturn(expectedFireStations);

        List<FireStationDto> result = fireStationService.findAll();

        assertThat(result).isEqualTo(expectedFireStations);
    }

    @Test
    public void testSave_return_true_when_fireStationEntity_is_Valid(){
        FireStationDto fireStation = new FireStationDto("Address1",1);

        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        FireStationDto result = fireStationService.save(fireStation);

        assertThat(result).isEqualTo(fireStation);
    }

    @Test
    public void testSave_return_false_when_fireStationEntity_is_invalid() {
        FireStationDto fireStation = new FireStationDto("Address1",1);

        when(fireStationRepository.save(fireStation)).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> fireStationService.save(fireStation))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testDelete_return_true()  {
        FireStationDto fireStation = new FireStationDto("Address1",1);

        when(fireStationRepository.delete(fireStation)).thenReturn(true);

        fireStationService.delete(fireStation);

        assertThat(fireStationRepository.delete(fireStation)).isTrue();
    }

    @Test
    public void testUpdate()  {
        FireStationDto fireStation = new FireStationDto("Address1",1);
        when(fireStationRepository.update(fireStation)).thenReturn(fireStation);

        assertThat(fireStationService.update(fireStation)).isEqualTo(fireStation);
    }

    @Test
    public void testDelete_return_success_when_fireStationEntity_is_valid()  {
        FireStationDto fireStation = new FireStationDto("Address1",1);

        when(fireStationRepository.delete(fireStation)).thenReturn(true);

        GenericResponseDto response = fireStationService.delete(fireStation);

        assertThat(response.success()).isTrue();
        assertThat(response.details())
                .isEqualTo(String.format("Fire station n°%s will no longer operate from the following address: %s", fireStation.station(), fireStation.address()));
    }

    @Test
    public void testDelete_return_error_when_fireStationEntity_is_invalid()  {
        FireStationDto fireStation = new FireStationDto("Address1",1);
        when(fireStationRepository.delete(fireStation)).thenReturn(false);

        GenericResponseDto response = fireStationService.delete(fireStation);

        assertThat(response.success()).isFalse();
        assertThat(response.details())
                .isEqualTo(String.format("Error: Fire station n°%s does not operate from the following address: %s", fireStation.station(), fireStation.address()));
    }

    @Test
    public void testFindStation_With_Address() {
        FireStationDto fireStation = new FireStationDto("Address1",1);
        String address = "Address1";

        when(fireStationRepository.findFireStation(address)
        ).thenReturn(fireStation);

        assertThat(fireStationService.findStation(address)).isEqualTo(fireStation.station());
    }
}
