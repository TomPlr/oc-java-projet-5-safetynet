package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safetynet.SafetyNetApplicationTests;
import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {SafetyNetApplicationTests.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class FireStationControllerTest {

    @Autowired
    private FireStationController fireStationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DataLoadJson.init();
        mockMvc = MockMvcBuilders.standaloneSetup(fireStationController).build();
    }

    @Test
    public void testFetchFireStations() throws Exception {

        mockMvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(13))
                .andReturn();
    }


    @Test
    public void testCreateFireStation() throws Exception {

        mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"123 Test Rd\",\"station\":1}"))
                .andExpect(jsonPath("$.address").value("123 Test Rd"))
                .andExpect(jsonPath("$.station").value(1))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    public void testUpdateFireStation() throws Exception {

        mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"1509 Culver St\",\"station\":1}"))
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.station").value(1))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"1509 Culver St\",\"station\":3}"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void testFindPersonsCoveredByFireStation() throws Exception {
        mockMvc.perform(get("/firestation").contentType(MediaType.APPLICATION_JSON).param("stationNumber", "1"))
                .andExpect(jsonPath("$.persons.length()").value(6))
                .andExpect(jsonPath("$.totalOver18").value(5))
                .andExpect(jsonPath("$.totalUnderOrEqual18").value(1))
                .andExpect(status().isOk())
                .andReturn();
    }

}
