package org.safetynet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void testCreatePerson() throws Exception {

        mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON)
                        .param("firstName", "Test").param("lastName", "Test"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testUpdatePerson() throws Exception {

        mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Test\",\"lastName\":\"Test\"}"))
                .andExpect(status().isOk())
                .andReturn();
    }


}
