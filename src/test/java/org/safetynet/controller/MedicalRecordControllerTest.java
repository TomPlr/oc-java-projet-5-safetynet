package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.service.MedicalRecordService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordControllerTest {

    @Mock
    private MedicalRecordService medicalRecordService;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController).build();
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {

        mockMvc.perform(post("/medicalRecord/").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord/").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/").contentType(MediaType.APPLICATION_JSON).param("firstName", "Test").param("lastName", "Test"))
                .andExpect(status().isOk()).andReturn();
    }
}
