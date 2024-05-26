package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.service.FireStationService;
import org.safetynet.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FireStationControllerTest {

    @Spy
    private PersonService personService;

    @Spy
    private FireStationService fireStationService;

    @InjectMocks
    private FireStationController fireStationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(fireStationController).build();
    }

    @Test
    public void testCreateFireStation() throws Exception {

        mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"Test\",\"station\":1}"))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    public void testUpdateFireStation() throws Exception {

        mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"Test\",\"station\":1}"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"Test\",\"station\":1}"))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void testFindPersonsCoveredByFireStation() throws Exception {
        mockMvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON).param("stationNumber", "1"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
