package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FireControllerTest {

    @Mock
    private PersonService personService;

    @Mock
    private FireStationService fireStationService;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private FireController fireController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(fireController).build();
    }

    @Test
    public void testFindPersonsWithMedicalHistory() throws Exception {

        mockMvc.perform(get("/fire").contentType(MediaType.APPLICATION_JSON).param("address", "test"))
                .andExpect(status().isOk())
                .andReturn()
        ;
    }
}
