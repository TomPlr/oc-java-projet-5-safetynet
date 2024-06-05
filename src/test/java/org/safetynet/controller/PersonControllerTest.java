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
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        DataLoadJson.init();
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testFetchAllPersons() throws Exception {

        mockMvc.perform(get("/person").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(23))
                .andReturn();
    }

    @Test
    public void testCreatePerson() throws Exception {

        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Test"))
                .andReturn();
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "John").param("lastName", "Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn();

    }

    @Test
    public void testUpdatePerson() throws Exception {

        mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"123 Test Rd\"}"))
                .andExpect(jsonPath("$.address").value("123 Test Rd"))
                .andExpect(status().isOk())
                .andReturn();
    }


}
