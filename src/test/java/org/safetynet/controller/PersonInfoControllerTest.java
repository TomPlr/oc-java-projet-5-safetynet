package org.safetynet.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.safetynet.mapper.PersonMapper;
import org.safetynet.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonInfoControllerTest {

    @Mock
    private PersonService personService;

    @Spy
    private PersonMapper personMapper;

    @InjectMocks
    private PersonInfoController personInfoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personInfoController).build();
    }

    @Test
    public void testFindPersonInfos() throws Exception {

        mockMvc.perform(get("/personInfo").contentType(MediaType.APPLICATION_JSON).param("firstName", "Test").param("lastName", "Test"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
