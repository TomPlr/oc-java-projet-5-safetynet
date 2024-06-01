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
public class MedicalRecordControllerTest {

    @Autowired
    private MedicalRecordController medicalRecordController;

    private MockMvc mockMvc;


    @BeforeEach
    void setup() {
        DataLoadJson.init();
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController).build();
    }

    @Test
    public void testFetchAllMedicalRecords() throws Exception {

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(jsonPath("$.length()").value(23))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {

        mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Test"))
                .andReturn();
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord/").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"birthdate\":\"01/01/1970\" }"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/").contentType(MediaType.APPLICATION_JSON).param("firstName", "John").param("lastName", "Boyd"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(status().isOk())
                .andReturn();
    }
}
